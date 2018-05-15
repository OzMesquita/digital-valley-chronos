/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author N2S
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController { 

    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        return "index";
    }
    
    @RequestMapping(value="/cadastrarAtividades", method = RequestMethod.POST)
    public String getPag2(Model model) {
        return "cadastrar-atividade";
    }
           
}
