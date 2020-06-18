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

                bool _IsValidToken = _BaseService.ValidateAPIToken(Data.APIKey);
                if (_IsValidToken == true)
                {
                    Obj = _Service.AddEditTournamentTeam(Data);
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