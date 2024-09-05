void setup() {
  Serial.begin(9600);
}

void loop() 
{
  Serial.print(sizeof(int));
  Serial.print(sizeof(char));
  Serial.print(sizeof( float));
  Serial.print(sizeof(unsigned int));
  Serial.print(sizeof(long int));
  Serial.print(sizeof(double));
  delay(10000000);
}
