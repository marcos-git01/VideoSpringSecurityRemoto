package com.egg.biblioteca.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresControlador implements ErrorController {

    //El @RequestMapping esta al nivel del metodo
    //Entra todo recurso que venga con /error, sea del metodo GET o POST
    //va a ingresar a este metodo
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    //Recuperamos el codigo de error que viene del servidor, y en base a eso
    //establecemos un mensaje en particular que pasa por el switch, y al final redirecciona a error.html
    //El metodo recibe una peticion http httpRequest
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) { 

        //creamos una pagina de error que seria error.html
        ModelAndView errorPage = new ModelAndView("error");

        String errorMsg = "";

        // tenemos un entero del codigo de status que sale del metodo getErrorCode
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "El recurso solicitado no existe.";
                break;
            }
            case 403: {
                errorMsg = "No tiene permisos para acceder al recurso.";
                break;
            }
            case 401: {
                errorMsg = "No se encuentra autorizado.";
                break;
            }
            case 404: {
                errorMsg = "El recurso solicitado no fue encontrado.";
                break;
            }
            case 500: {
                errorMsg = "Ocurrió un error interno.";
                break;
            }
        }
        
        //en la pagina errorPage injecta el codigo y el mensaje
        errorPage.addObject("codigo", httpErrorCode);
        errorPage.addObject("mensaje", errorMsg);
        return errorPage;
    }

    //el metodo recibe la peticion http, y trae el atributo 
    //del status del codigo y castearlo a un entero y de esta forma nos retorna
    //en formato de entero el codigo de estatus de la peticion
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    public String getErrorPath() {
        return "/error.html";
    }

}
