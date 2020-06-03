using Volleyball.EF;

namespace VolleyballService.Services
{
    public class CConnect
    {
       public DB_A43E53_volleyballEntities DC;  
        public CConnect()
        {
            DC = new DB_A43E53_volleyballEntities();
        }
    }
}
