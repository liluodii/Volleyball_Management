using System;
using System.Configuration;
using System.Net.NetworkInformation;
using Newtonsoft.Json;
using Volleyball.EF;
namespace VolleyballService.Services
{

    public class BaseService
    {

        public static string TOKEN = "123";
        public DB_A43E53_volleyballEntities DC = new DB_A43E53_volleyballEntities();
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
        public bool ValidateAPIToken(string APIKey, string JData, string MethodName, string Res = "")
        {
            if (MethodName != "" && JData != "")
                ServiceLogException(null, MethodName, JData, Res);
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
        public static String GetURL()
        {
            return ConfigurationManager.AppSettings["ImagePath"];
        }
        public string ConvertJsontoString(object Data)
        {
            string result = JsonConvert.SerializeObject(Data);
            return result;
        }
        public void ServiceLogException(System.Exception ex, string MethodName, string Data, string Res)
        {
            try
            {
                AppException _Exception = new AppException();
                _Exception.UserID = null;
                _Exception.Message = Res;
                _Exception.Method = MethodName;
                _Exception.Page = "";
                _Exception.Line = ex == null ? "0" : Convert.ToString(new System.Diagnostics.StackTrace(ex, true).GetFrame(0).GetFileLineNumber());
                _Exception.Object = Data;
                
                _Exception.CreatedDate = System.DateTime.UtcNow;
                _Exception.ExceptionType = 2;
                _Exception.IsSolved = false;
                DC.AppExceptions.Add(_Exception);
                DC.SaveChanges();
            }
            catch
            {
            }
        }

    }
}
