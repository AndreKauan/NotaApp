/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import entites.Materia;

/**
 *
 * @author Antonelli
 */
public class AppBLL {
    
    public static void validaDados(Materia nvMateria){
        
        Erro.setErro(false);
        //Valida preenchimento da materia
        if(nvMateria.getMateria().equals(""))
            {Erro.setErro("O campo MATERIA é de preenchimento obrigatório..."); return;}
        
        //Valida preenchimento e tipo da p1
        if(nvMateria.getP1().equals(""))
            {Erro.setErro("O campo P1 é de preenchimento obrigatório..."); return;}
        else
            try{
                Integer.parseInt(nvMateria.getP1());
            }catch(Exception e){
                Erro.setErro("O campo P1 deve ser numerico!"); return;
            }
        
        //Valida preenchimento e tipo da p2
        if(nvMateria.getP2().equals("") || nvMateria.getP2().equals(null))
            nvMateria.setP2(null);
        else
            try{
                Integer.parseInt(nvMateria.getP2());
            }catch(Exception e){
                Erro.setErro("O campo P2 deve ser numerico!"); return;
            }
        
        //Valida preenchimento e tipo da p3
        if(nvMateria.getP3().equals("") || nvMateria.getP3().equals(null))
            nvMateria.setP3(null);
        else
            try{
                Integer.parseInt(nvMateria.getP3());
            }catch(Exception e){
                Erro.setErro("O campo P3 deve ser numerico!"); return;
            }
    }
    
    public static void inserirNota(Materia nvMateria){
        try{
            AppDAL.conecta("Notas.mdb");
            if (Erro.getErro()) return;
            AppDAL.inserirNota(nvMateria);
            if (Erro.getErro()) return;
            AppDAL.desconecta();
        }catch(Exception e){
            Erro.setErro(e.getMessage());
        }
    }
    
    public static void validaMateria(Materia nvMateria){
        
        Erro.setErro(false);
        if (nvMateria.getMateria().equals(""))
            {Erro.setErro("O campo MATERIA é de preenchimento obrigatório..."); return;}
        
        AppDAL.conecta("Notas.mdb");
        if (Erro.getErro()) return;
        
        AppDAL.consultaMateria(nvMateria);
        
        if (Erro.getErro()) return;
        AppDAL.desconecta();
    }
    
    public static void alteraNota(Materia nvMateria){
        
        Erro.setErro(false);
        
        //Verificando que todos os campos estejam preenchidos
        
        
        try{
            AppDAL.conecta("Notas.mdb");
            if (Erro.getErro()) return;
            
            AppDAL.update(nvMateria);
            if (Erro.getErro()) return;
            
            AppDAL.desconecta();
        }catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }
    
    
    public static String VerificaStatus(Materia nvMateria){
        double p1 = Double.parseDouble(nvMateria.getP1());
        double p2 = 0;
        double p3;
        double avg = 0;
        double avgBase = 5;
                
        if(nvMateria.getP2()==null)
            return "Nota minima na P2: " + (3 * avgBase - p1) / 2 + "pts";
        if(nvMateria.getP2()!=null && nvMateria.getCampoP3()==false){
            p2 = Double.parseDouble(nvMateria.getP2());
            avg = (p1 + 2*p2)/3;
            
            //"Arredondamento"
            if (avg < 4.5) {
                nvMateria.setCampoP3(true);
            }
            
            nvMateria.setAvg(String.valueOf(avg));
            return "Media Total = " + String.format("%.2f", avg) + "pts";
        }
        if (nvMateria.getP3()==null){
            p2 = Double.parseDouble(nvMateria.getP2());
            
            double maiorNota = 0;
            if (p1>p2)
                maiorNota = p1;
            else
                maiorNota = p2;
            
            return "[Media atual: " + nvMateria.getAvg() + "] Nota minima na P3: " + (3 * avgBase - maiorNota) / 2 + "pts";
        }
        if(nvMateria.getP3()!=null){
            p2 = Double.parseDouble(nvMateria.getP2());
            p3 = Double.parseDouble(nvMateria.getP3());
            double maiorNota = 0;
            if (p1>p2)
                maiorNota = p1;
            else
                maiorNota = p2;
            
            avg = (maiorNota + 2*p3)/3;
            if(avg>4.5)
                return "Media Total = " + String.format("%.2f", avg) + "pts. Aprovado!";
            else
                return "Media Total = " + String.format("%.2f", avg) + "pts. Reprovado!";
        }
            
        return null;
    }
}
