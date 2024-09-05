float ai = 0;
float a = 0; 
String cadena = "";

bool Altui = true;
bool Altu,tiempo, nonum = false;

void setup() {
  Serial.begin(9600);

Serial.println("Ingrese los metros de Altura desde donde lo lanza al objeto");
}

void loop() {
  if (nonum)
  {
    Serial.println("////////// solo numeros //////////");
    nonum = !nonum;
  }

  
  if(cadena != "" )
  {
    if (Altui)
    {
      ai = String(cadena).toFloat();
      Serial.println(ai);
      cadena = ""; 
      Serial.println("Y los metros de Altura donde pase el abjeto");
      Altui = !Altui;
    }
    else
    {
       a =  String(cadena).toFloat();
       Serial.println(a);
       cadena = "";
       tiempo = !tiempo;
    }
  }
  if (tiempo)
  {
    if (ai >= a)
    {
      float t = sqrt((2*(ai-a))/9.81);
      Serial.print("Tardara en llegar ");
      Serial.print(t);
      Serial.println(" segundos");
      Serial.println("");
      Serial.println("Ingrese la Altura desde donde lo lanza a objeto");

    }
    else
    {
      Serial.println("La Altura inicial tiene que ser mayor");
      Serial.println("");
      Serial.println("Ingrese la Altura desde donde lo lanza a objeto");
    }
    tiempo = !tiempo;
    Altui = !Altui;
  }
}
void serialEvent(){
 while (Serial.available() > 0)
  {
     char num = Serial.read();
     
     if (num != '\n' && num != '\r')
     {
      if (isDigit(num) || num == '.')
      {
        cadena += num;
      }
     }
     delay(100);
  }
  
  if (cadena.lastIndexOf('.') == cadena.indexOf('.'))
  {
    // no es necesario hacer algo
  }
  else
  {
    nonum = !nonum;
    cadena = "";
  }
}
  
