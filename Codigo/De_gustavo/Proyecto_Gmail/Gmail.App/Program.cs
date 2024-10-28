using Gmail.Libreria.serivicio;

class Program
{
   static void Main(){
    DateTime hoy = DateTime.UtcNow;
    TimeZoneInfo argentinaTimeZone = TimeZoneInfo.FindSystemTimeZoneById("Argentina Standard Time");
    DateTime date = TimeZoneInfo.ConvertTimeFromUtc(hoy, argentinaTimeZone);
    string diaGuardado = date.ToString("yyyy-MM-dd HH:mm:ss");

    EnviarGmail e = new EnviarGmail("nachotom02@gmail.com", "Este mensaje se envio el " + diaGuardado , "se le agradese de su participacion");
   }
    
}
