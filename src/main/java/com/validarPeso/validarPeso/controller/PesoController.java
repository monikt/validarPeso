/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validarPeso.validarPeso.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Monica
 */
@Controller
public class PesoController {

    static Logger LOG = Logger.getLogger(PesoController.class.getName());

    @GetMapping("/")
    public String viewHomePage(Model model) {
      return "index";  
    }

    @PostMapping("/calcularPeso")
    public String calcularPeso(@RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "peso") String peso,
            @RequestParam(value = "estatura") String estatura,
            Model model) {

        String resultado = null;
        if (peso != null && estatura != null) {
            try {
                Integer pesoi = Integer.parseInt(peso);
                Double estaturad = Double.parseDouble(estatura);
                Double imc = pesoi / (estaturad * estaturad);
                model.addAttribute("nombre", nombre);
                model.addAttribute("peso", peso);
                model.addAttribute("estatura", estatura);
                NumberFormat formatter = new DecimalFormat("#00.00");  
                resultado = "Su índice de masa corporal es " + formatter.format(imc) + ". Usted se encuentra en : ";
                if (imc < 18.5) {
                    resultado = resultado + "Peso Bajo";
                } else if (imc >= 18.5 && imc < 24.9) {
                    resultado = resultado + "Peso Normal";
                } else if (imc >= 25.0 && imc < 29.9) {
                    resultado = resultado + "Peso Alto";
                } else if (imc > 30.0) {
                    resultado = resultado + "Obesidad";
                } else {
                }
                 model.addAttribute("resultado", resultado);

            } catch (NumberFormatException e) {
                model.addAttribute("mensaje", "Se presento un error realizando la conversión de unidades");
                return "index";
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Error calculado IMC error :" + e.getMessage());
                model.addAttribute("mensaje", "Se presento un error ejecutando el proceso");
                return "index";
            }

        }else {
          model.addAttribute("mensaje", "Los campos no pueden ser vacios");
          return "index";
        }

        return "mostrar_resultado";
    }
}
