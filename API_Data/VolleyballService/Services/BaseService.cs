using System;

using System.Net.NetworkInformation;
using Newtonsoft.Json;

namespace VolleyballService.Services
{

    public class BaseService
    {

        string TOKEN = "123";

        public bool ValidateAPIToken(string APIKey)
        {
           
            bool _IsValid = false;
            try
            {
                if (TOKEN != APIKey)
                {
                    _IsValid = false;
                }
                else
                {
                    _IsValid = true;
                }
            }
            catch (System.Exception ex)
            {
                _IsValid = false;
            }
            return _IsValid;
        }


       
    }
}
