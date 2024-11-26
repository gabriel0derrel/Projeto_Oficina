/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;
import java.util.ArrayList;
import modelo.classes.Professor;
/**
 *
 * @author ejmcc
 */
public interface IProfessor {
    void create(Professor objetoAluno)throws Exception;
    ArrayList<Professor> listaDeProfessores()throws Exception;
    void delete(int matricula)throws Exception;
}
