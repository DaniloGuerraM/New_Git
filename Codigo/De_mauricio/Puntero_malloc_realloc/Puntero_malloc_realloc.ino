void setup() {

  Serial.begin(115200);
  
  int *punt;
  int caracter = 4;
  punt = &caracter;

  Serial.println(String("primer valor"));
  Serial.println(*punt);
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)punt,(HEX));
  Serial.println(String(""));
  
  *punt = 5;

  Serial.println(String("Segundo valor"));
  Serial.println(*punt);
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)punt,(HEX));
  Serial.println(String("-------------------------------"));

  Serial.println(String("Metodo: malloc"));
  punt = (int *) malloc(2* sizeof(int));
  *(punt+0) = 3;
  *(punt+1) = 2;

  Serial.println(String("primer valor"));
  Serial.println(*(punt+0));
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)(punt+0),(HEX));
  Serial.println(String(""));

  Serial.println(String("segundo valor"));
  Serial.println(*(punt+1));
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)(punt+1),(HEX));
  Serial.println(String("-------------------------------"));
  
  Serial.println(String("Metodo: realloc"));
  punt = (int *) realloc(punt, 3* sizeof(int));
  *(punt+2) = *(punt+1)+2;

  Serial.println(String("primer valor"));
  Serial.println(*(punt+0));
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)(punt+0),(HEX));
  Serial.println(String(""));

  Serial.println(String("segundo valor"));
  Serial.println(*(punt+1));
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)(punt+1),(HEX));
  Serial.println(String(""));


  Serial.println(String("tercero valor"));
  Serial.println(*(punt+2));
  Serial.println(String("su direccion de memoria"));
  Serial.println((int)(punt+2),(HEX));
  Serial.println(String("-------------------------------"));

  

 
}

void loop() {
  // put your main code here, to run repeatedly:

}
