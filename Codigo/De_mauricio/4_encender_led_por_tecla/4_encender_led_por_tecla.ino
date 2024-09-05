
void encenderLed (int num);


// este es el pin de salida
int Pinled = 13; 
// estado del led
int estado = 0;

void setup() {
  //inicializamos el puerto serial
  Serial.begin(9600);

  //configuramos el pin de salida
  pinMode (Pinled, OUTPUT);
 }

void loop() {
//se guarda la tecla precionada
char tecla = Serial.read();

//pregunta si es una letra
  if (isAlpha(tecla) )
  {
    // si es una vocal iguanla estado 1
    if (tecla == 'a' || tecla == 'e' || tecla == 'i' || tecla == 'o' || tecla == 'u')
    {
      estado = 1;
    }
    // si no, es porque es una consonante, lo iguala a 0
    else
    {
      estado  = 0;
    }
    // imprime texto mas la letra que se ingreso
    Serial.print("La letra es: ");
    Serial.println(tecla);
    // llama la fincion encenderLed
    encenderLed(estado);
  }

}




void encenderLed (int num)
{
  // si el valor que se ingreso es 1 enciende led y imprime encendido
  if (num)
  {
    digitalWrite(Pinled,HIGH);
    Serial.println("Encendido");
  }
  // sino apaga el led y imprime apagado
  else
  {
    digitalWrite(Pinled, LOW);
    Serial.println("Apagado");
  }
}
