
void setup(){
  Serial.begin(9600);
  Serial.println("Introdusca caracter");
}

void loop(){

int num = 0;
char *punt;
char caracter;
bool imprimo = false;
punt = (char *) malloc(sizeof(char));

while (true)
{
  if (Serial.available() > 0)
  {
    caracter = Serial.read();
   
    if (caracter != '\n'  && caracter != '\r') //&& caracter != -1
    {
      imprimo = true;
      *(punt+num) = caracter;
      num++;
      punt = (char *)realloc(punt, (num + 1)* sizeof(char));
      punt[num] = caracter;
    }
    else 
    {
      break;
    }
  }
}
if (imprimo)
{

  *(punt+num) = '\0';
  for (int i= 0; i< num; i++)
  {
    Serial.print(punt[i]);
    Serial.print("");
  
  }
  Serial.println("");
  Serial.print("nuemro de caracteres: ");
  Serial.println(num);
  imprimo = false;  
}

free(punt);
}
