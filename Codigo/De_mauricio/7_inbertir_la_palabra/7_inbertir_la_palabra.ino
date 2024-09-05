void setup() {
  Serial.begin(9600);
  Serial.println("Instrodusca cualquier tecla");
}

void loop() {
  int num = 0;
  char *punt;
  char cart;
  bool imprime = false;
  punt = (char *) malloc(sizeof(char));
  while(true)
  {
    if (Serial.available() > 0)
      {
        
        cart = Serial.read();
        if (cart != '\n'  && cart !=  '\r') //cart !=  '\0' && cart != -1 
        {
          *(punt+num) = cart;
          //Serial.println(num);
          num++;
          //Serial.println(num);
          punt = (char *)realloc(punt, (num + 1)* sizeof(char));
          punt[num] = cart;
          imprime = true;   
        }else{

          break;
         }
      }
  }
  if (imprime)
  {


    *(punt+num) = '\0';

    for (int i = num; i >=0; i--)
    {
      Serial.print("");
      Serial.print(punt[i]);
    }
    
    imprime = false;
    Serial.println("");
    Serial.print("numero de letras: ");
    Serial.println(num);
  }
  free(punt);
}
