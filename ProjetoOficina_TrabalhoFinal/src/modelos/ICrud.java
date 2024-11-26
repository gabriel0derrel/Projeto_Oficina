/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos;

import java.util.List;

/**
 *
 * @author misuka
 */
public interface ICrud<T> {
    public void incluir(T objeto) throws Exception;
    public void alterar(T objeto) throws Exception;
    public T consultar(T objeto) throws Exception;
    public List<T> listar() throws Exception;
}
