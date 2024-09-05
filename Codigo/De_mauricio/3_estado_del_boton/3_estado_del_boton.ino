//Escribir un programa que muestre como se utiliza el puerto serial para controlar el estado de un
//periférico (botón pulsador, sensor etc). Ante una acción del periférico se debe visualizar un mensaje
//en el monitor serie del IDE de Arduino.


// Configurar el pin del botón 
int boton = 8;

void setup() {
    // Inicializar el puerto serial
    Serial.begin(9600);

    // Configurar el pin del botón como entrada
    pinMode(boton, INPUT_PULLUP);
}

void loop() {
    // Leer el estado del botón
    int estadoBoton = digitalRead(boton);

    // Si el botón está presionado
    if (estadoBoton == HIGH) {
        // Enviar un mensaje al monitor serie
        Serial.println("Botón no presionado");
    } else {
        // Enviar un mensaje al monitor serie
        Serial.println("Botón presionado");
    }

    // Esperar un breve tiempo antes de volver a verificar el estado del botón
    delay(0);
}
