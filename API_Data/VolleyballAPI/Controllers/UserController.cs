﻿using System;
using System.Collections.Generic;
using System.Linq;

using System.Web.Http;

using Volleyball.Common;

using VolleyballService.Services;
using static Volleyball.EF.CustomClasses.CRequest;



namespace VolleyballAPI.Controllers
{
    public class UserController : ApiController
    {
        public BaseService _BaseService = new BaseService();
        UserService _Service = new UserService();

        [Route("Login")]
        [HttpPost]
        public GenericClass Login(CReqUserLogin Data)
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
                    Obj = _Service.Login(Data);
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


        [Route("ChangePassword")]
        [HttpPost]
        public GenericClass ChangePassword(CReqChangePass Data)
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
                    Obj = _Service.ChangePassword(Data);
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


        [Route("UpdateProfile")]
        [HttpPost]
        public GenericClass UpdateProfile(CReqUpdateProfile Data)
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
                    Obj = _Service.UpdateProfile(Data);
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


        [Route("ForgetPassword")]
        [HttpPost]
        public GenericClass ForgetPassword(CReqForgetPassword Data)
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
                    Obj = _Service.ForgetPassword(Data);
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