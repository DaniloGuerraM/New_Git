#include <SoftwareSerial.h>
SoftwareSerial tuSerial(10, 11);//RX 10, TX 11
String numal = "";
long num;
long num2;
void setup() {
  Serial.begin(9600);
  tuSerial.begin(9600);
  tuSerial.print("r");
}

void loop() {
  num = random(0, 15);
  if (num < 10)
  {
    numal = "0";
    numal += num;  
  }
  else
  {
    numal = num;
  }

  num2 = random(0, 15);
  if (num2 < 10)
  {
    numal += "0";
    numal += num2;  
  }
  else
  {
    numal += num2;
  }
  tuSerial.println(numal);
  delay(500);
  Serial.println(numal);
  delay(6000);
  while (true)
  {
    if (tuSerial.available())
    {
      char tu = tuSerial.read();
      switch (tu)
      {
        case 'a':
          Serial.println("agua");
          break;
        case 'i':
          Serial.println("impacto");
          break;
        case 'h':
          Serial.println("hundido");
          break;
        case 'e':
          Serial.println("error de coordenadas");
          break;
        default:
          break;
      }
      break;
    }
  }
  bool es = true;
  while (es)
  {
    if (enviar())
    {
      es = !es; 
    }
  }
  delay(3000);
}
bool enviar()
{
  String tu;
  while (true)
  {
    if (tuSerial.available()> 0)
    {
      tu = tuSerial.readStringUntil('\n');
      break;
    }
  }
 if (tu.length() == 4 )
 {
  if (isDigit(tu[0]) && isDigit(tu[1])&& isDigit(tu[2])&& isDigit(tu[3]))
  {
    String fi = "";
    String co = "";
    fi += tu[0];
    fi += tu[1];
    co += tu[2];
    co += tu[3];
    int fil = String(fi).toInt();
    int colum = String(co).toInt();
    if (fil <15 && fil >=0 && colum < 15 && colum >= 0)
    {
      int nume = random(0,3);
      switch (nume)
      {
        case 0:
          tuSerial.print("a");
          break;
        case 1:
          tuSerial.print("i");
          break;
        case 2:
          tuSerial.print("h");
          break;
         default:
          break;
      }
      return true;
    }
    else
    {
      tuSerial.print("e");
      return false;
    }
  }
  else
  {
    tuSerial.print("e");
    return false;
  }
 }
 else
 {
  tuSerial.print("e");
  return false;
 }
}
