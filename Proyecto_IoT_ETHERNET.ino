#include <Servo.h>
#include <EtherCard.h>
#define APIKEY "9VNJT2ODSQ7VLVI6"

static byte mymac[] = { 0x74, 0x69, 0x69, 0x2D, 0x30, 0x31 };
const char website[] PROGMEM = "api.thingspeak.com";
byte Ethernet::buffer[700];
uint32_t timer;
byte session;
Stash stash;

Servo ventanas;
int res = 100;
int gas;
int tiempo;
int zumbador = 3;
int ledRojo = 4;
int ledVerde = 5;
int motor = 6;

void setup () {
  pinMode(A0, INPUT);
  pinMode(ledRojo, OUTPUT);
  pinMode(ledVerde, OUTPUT);
  pinMode(motor, OUTPUT);
  pinMode(zumbador, OUTPUT);
  ventanas.attach(9);
  Serial.begin(9600);
  Serial.println("------Datos de Conexion------");
  initialize_ethernet();
  Serial.println("-----------------------------");
}

void loop () {
  res = res + 1;
  ether.packetLoop(ether.packetReceive());
  if (res == 200) {
    byte sd = stash.create();
    stash.print("field1=");
    stash.print(gas);
    Serial.print("Valor de Gas: ");
    Serial.println(gas);

    gas = analogRead(A0);
    if (gas >= 400) {
      digitalWrite(ledRojo, HIGH);
      digitalWrite(ledVerde, LOW);
      tone (zumbador, 524, 250);
      Serial.print("Valor: ");
      Serial.println(gas);
      tiempo += 400;
      if (tiempo > 5000) {
        ventanas.write(360);
        digitalWrite(motor, HIGH);
      }
      delay(500);
    } else {
      digitalWrite(motor, LOW);
      digitalWrite(ledVerde, HIGH);
      digitalWrite(ledRojo, LOW);
      ventanas.write(0);
      tiempo = 0;
    }

    stash.save();
    Stash::prepare(PSTR("POST /update HTTP/1.0" "\r\n"
                        "Host: $F" "\r\n"
                        "Connection: close" "\r\n"
                        "X-THINGSPEAKAPIKEY: $F" "\r\n"
                        "Content-Type: application/x-www-form-urlencoded" "\r\n"
                        "Content-Length: $D" "\r\n"
                        "\r\n"
                        "$H"),
                   website, PSTR(APIKEY), stash.size(), sd);
    session = ether.tcpSend();
    int freeCount = stash.freeCount();
    if (freeCount <= 3) {
      Stash::initMap(56);
    }
  }
  const char* reply = ether.tcpReply(session);
}

void initialize_ethernet(void) {
  for (;;) {
    if (ether.begin(sizeof Ethernet::buffer, mymac, SS) == 0) {
      Serial.println( "Error al acceder al controlador Ethernet");
      continue;
    }
    if (!ether.dhcpSetup()) {
      Serial.println("DHCP no responde");
      continue;
    }
    ether.printIp("Dirección IP:     ", ether.myip);
    ether.printIp("Puerta de Enlace: ", ether.gwip);
    ether.printIp("Servidor DNS:     ", ether.dnsip);
    if (!ether.dnsLookup(website))
      Serial.println("DNS no responde");
    res = 180;
    break;
  }
}
