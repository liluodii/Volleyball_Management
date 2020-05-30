using Volleyball.EF;

namespace VolleyballService.Services
{
    public class CConnect
    {
       public VolleyballEntities DC;  
        public CConnect()
        {
            DC = new VolleyballEntities();
        }
    }
}
