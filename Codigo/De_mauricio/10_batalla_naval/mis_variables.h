#include <SoftwareSerial.h>
SoftwareSerial tuSerial(10, 11);

#define Fi 15
#define C 15

int gane = 0;
int perdi = 0;

char que;
String envio = "";

char tablero1[Fi][C] ; // Inicializa el primer tablero
char tablero2[Fi][C] ; // Inicializa el segundo tablero

bool escucho = true;
