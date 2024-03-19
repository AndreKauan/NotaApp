/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import entites.Materia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Antonelli
 */
public class AppDAL {
    
    private static Connection con;

    //Coneccao com o servidor
    public static void conecta(String _bd)
    {
        Erro.setErro(false);
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://" + _bd);
        }
        catch (Exception e)
        {
            Erro.setErro(e.getMessage());
        }
    }
    //Desconecao com o servidor
    public static void desconecta()
    {
        Erro.setErro(false);
        try 
        {
            con.close();
        }
        catch (Exception e)
        {
            Erro.setErro(e.getMessage());
        }
    }
    
    public static void inserirNota(Materia nvMateria){
        
        Erro.setErro(false);
        try{
            PreparedStatement st = con.prepareStatement("insert into TABNOTAS (materia,p1,p2,p3) values (?,?,?,?)");
            st.setString(1, nvMateria.getMateria());
            st.setString(2, nvMateria.getP1());
            st.setString(3, nvMateria.getP2());
            st.setString(4, nvMateria.getP3());
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            Erro.setErro(e.getMessage());
        }
    }
    
    public static void consultaMateria(Materia nvMateria){
        
        ResultSet rs;
        
        try{
            PreparedStatement st = con.prepareStatement("SELECT * FROM TABNOTAS WHERE materia=?");
            st.setString(1,nvMateria.getMateria());
            rs = st.executeQuery();
            if(rs.next()){
                nvMateria.setP1(rs.getString("p1"));
                nvMateria.setP2(rs.getString("p2"));
                nvMateria.setP3(rs.getString("p3"));
            }else{
                Erro.setErro("Materia nao encontrada.");
                return;
            }
            st.close();
        }catch(Exception e){
            Erro.setErro(e.getMessage());
        }
    }
    
    public static void update (Materia nvMateria){
        
        Erro.setErro(false);
        try{
            PreparedStatement st = con.prepareStatement("UPDATE TABNOTAS SET p1=?, p2=?, p3=?,avg=? WHERE materia=?");
            st.setString(1, nvMateria.getP1());
            st.setString(2, nvMateria.getP2());
            st.setString(3, nvMateria.getP3());
            st.setString(4, nvMateria.getAvg());
            st.setString(5, nvMateria.getMateria());
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            Erro.setErro(e.getMessage());
        }
    }
}
