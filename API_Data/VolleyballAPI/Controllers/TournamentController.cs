using System;
using System.Collections.Generic;
using System.Linq;

using System.Web.Http;

using Volleyball.Common;

using VolleyballService.Services;
using static Volleyball.EF.CustomClasses.CRequest;



namespace VolleyballAPI.Controllers
{
    public class TournamentController : ApiController
    {
        public BaseService _BaseService = new BaseService();
        TournamentService _Service = new TournamentService();


        [Route("AddEditTournamet")]
        [HttpPut]
        public GenericClass AddEditTournamet(CReqAddEditTournament Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                bool _IsValidToken = _BaseService.ValidateAPIToken(Data.APIKey);
                if (_IsValidToken == true)
                {
                    Obj = _Service.AddEditTournamet(Data);
                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("DeleteTournament")]
        [HttpDelete]
        public GenericClass DeleteTournament(CReqDeleteTournament Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                bool _IsValidToken = _BaseService.ValidateAPIToken(Data.APIKey);
                if (_IsValidToken == true)
                {
                    Obj = _Service.DeleteTournament(Data);
                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("DeleteTournamentTeam")]
        [HttpDelete]
        public GenericClass DeleteTournamentTeam(CReqDeleteTournamentTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                string JData = _BaseService.ConvertJsontoString(Data);
                bool _IsValidToken = _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName);

                if (_IsValidToken == true)
                {
                    Obj = _Service.DeleteTournamentTeam(Data);
                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }



        [Route("AddEditTournamentTeam")]
        [HttpPut]
        public GenericClass AddEditTournamentTeam(CReqAddEditTournamentTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.AddEditTournamentTeam(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

        [Route("UpdateScore")]
        [HttpPut]
        public GenericClass UpdateScore(CReqUpdateScore Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                bool _IsValidToken = _BaseService.ValidateAPIToken(Data.APIKey);
                if (_IsValidToken == true)
                {
                    
                }
                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.UpdateScore(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("GetMatchList")]
        [HttpGet]
        public GenericClass GetMatchList(int UserID)
        {
            GenericClass Obj = new GenericClass();
            try
            {

                Obj = _Service.GetMatchList();

                
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);

               
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

        [Route("GetTournamentList")]
        [HttpGet]
        public GenericClass GetTournamentList(int UserID)
        {
            GenericClass Obj = new GenericClass();
            try
            {

                Obj = _Service.GetTournamentList();

                string JData1 = _BaseService.ConvertJsontoString(Obj);

                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);

            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("GetTournamentTeam")]
        [HttpPost]
        public GenericClass GetTournamentTeam(CReqGetTournament Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {

                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.GetTournamentTeam(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }

            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

    }
}