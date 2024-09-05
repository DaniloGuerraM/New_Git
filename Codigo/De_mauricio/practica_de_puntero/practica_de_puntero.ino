int *punt;
void setup(){
  Serial.begin(9600);
}

void loop(){
  Serial.println("espero que funcione");
  
  punt = (int *) malloc(2 * sizeof(int));
  *(punt+0) = 1;
  *(punt+1) = 2;  
  punt = (int *) realloc(punt, 3 * sizeof(int));
  *(punt+2) = *(punt+1) + 1;
  Serial.println(String("Test :") + punt[0]+  String("_") + punt[1] + String("_") + punt[2]);
  delay(1000);
  
}
