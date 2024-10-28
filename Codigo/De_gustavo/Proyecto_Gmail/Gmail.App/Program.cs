using Gmail.Libreria.serivicio;

class Program
{
   static void Main(){
      string cuerpo = "En un pequeño pueblo donde la luz del sol parecía bailar, vivía un perro llamado Max, que tenía una peculiaridad: ‍ perseguía sombras.‌ Max no era ⁣un perro común; cada vez que salía‍ a jugar, sus ojos brillaban‌ con emoción al ver sombras moverse. Se lanzaba tras ellas como si fuera el mejor de ⁢los cazadores, pero en realidad, se encontraba tras silhouette de ​árboles, nubes y hasta mariposas.​ Al hacer esto, no solo causaba sonrisas entre los niños ⁢del vecindario, sino que también creaba ​situaciones muy graciosas. A menudo los veían correr, tras ‍la inesperada sombra de un gato, solo para darse cuenta de que había sido un simple​ juego de luces.​ Nadie podía‌ resistirse a⁢ las carcajadas que‍ provocaba."; 
    DateTime hoy = DateTime.UtcNow;
    TimeZoneInfo argentinaTimeZone = TimeZoneInfo.FindSystemTimeZoneById("Argentina Standard Time");
    DateTime date = TimeZoneInfo.ConvertTimeFromUtc(hoy, argentinaTimeZone);
    string diaGuardado = date.ToString("yyyy-MM-dd HH:mm:ss");

    EnviarGmail e = new EnviarGmail("nachotom02@gmail.com", "Llego el " + diaGuardado , cuerpo);
   }
    
}
