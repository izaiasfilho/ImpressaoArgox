/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impressoras;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zse
 */
public class argox {
    
    
//metodo com codidgo para imprimir
    public static void impressaoETIQUETA_ARGOX(String caminho, String usuarioWindows) {
        String doc = caminhoOndeVaiSerSalvo("_GOND",usuarioWindows);
        try {
            File arquivo = new File(doc);//novo arquivo
            arquivo.delete();
            arquivo.createNewFile();
            if (arquivo.exists()) {
                FileWriter arquivoTxt = new FileWriter(arquivo, true);
                PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
                linhasTxt.println("" + (char) 27 + (char) 64 + "n");
                linhasTxt.println("" + (char) 27 + (char) 64 + "M0500");
                linhasTxt.println("" + (char) 27 + (char) 64 + "O0220");
                linhasTxt.println("" + (char) 27 + (char) 64 + "V0");
                linhasTxt.println("" + (char) 27 + (char) 64 + "f220");
                linhasTxt.println("" + (char) 27 + (char) 64 + "D");
                linhasTxt.println("" + (char) 27 + (char) 64 + "L");
                linhasTxt.println("" + (char) 27 + (char) 64 + "D11");
                linhasTxt.println("" + (char) 27 + (char) 64 + "A2");
                linhasTxt.println("" + (char) 27 + (char) 64 + "14110000162021TESTEETIQUETA");
                linhasTxt.println("" + (char) 27 + (char) 64 + "1e6305000930034C11&D3528&&E-&D7488");
                linhasTxt.println("" + (char) 27 + (char) 64 + "14210000073002611 3528-7488");
                linhasTxt.println("" + (char) 27 + (char) 64 + "111200000380106WWWKKKKPPPPJJ");
                linhasTxt.println("" + (char) 27 + (char) 64 + "Q0001");
                linhasTxt.println("" + (char) 27 + (char) 64 + "E");
                int i = 0;
                while (i < 2) {
                    i++;
                    linhasTxt.println();
                }
                arquivoTxt.close();
            } else {
                arquivo.createNewFile();
            }
        } catch (IOException error) {
            System.out.println("nao encontrei arquivo");
        }
        enviarImpressora(doc,caminho);
    }

    
    //metodo com codidgo para imprimir
    public static void teste2(String caminho,String usuarioWindows) {
        String doc = caminhoOndeVaiSerSalvo("_GOND",usuarioWindows);
        try {
            File arquivo = new File(doc);//novo arquivo
            arquivo.delete();
            arquivo.createNewFile();
            if (arquivo.exists()) {
                PrintWriter fo;
                fo = new PrintWriter(new FileOutputStream(arquivo));
                fo.print((char) 2);
                fo.print("L");
                fo.print((char) 13);
                fo.print("H10");
                fo.print((char) 13);
                fo.print("D11");
                fo.print((char) 13);
                fo.print("1E9308000110020" + "tste");
                fo.print((char) 13);
                fo.print("Q001");
                fo.print((char) 13);
                fo.print("E");
                fo.print((char) 13);
                fo.print("f320");
                fo.print((char) 13);
//
//                fo.print("n");
//                fo.print("M0500");
//                fo.print("O0220");
//                fo.print("V0");
//                fo.print("f220");
//                fo.print("D");
//                fo.print("L");
//                fo.print("D11");
//                fo.print("A2");
//                fo.print("14110000162021TESTEETIQUETA");
//                fo.print("1e6305000930034C11&D3528&&E-&D7488");
//                fo.print("14210000073002611 3528-7488");
//                fo.print("111200000380106WWWKKKKPPPPJJ");
//                fo.print("Q0001");
//                fo.print("E");
//                fo.close();

                int i = 0;
                while (i < 2) {
                    i++;
                    fo.println();
                }
                fo.close();
            } else {
                arquivo.createNewFile();
            }
        } catch (IOException error) {
            System.out.println("nao encontrei arquivo");
        }

       enviarImpressora(doc,caminho);
    }
    
    
//############################### ABAIXO FICA OS CODIGOS COMPLEMENTARES, MAS NAO PRECISA MERXER    
    
    
    
//#######################################  CAMINHO DO ARQUIVO
    public static String caminhoOndeVaiSerSalvo(String lista, String usuarioWindows) {
        String OS = getOS(usuarioWindows);//+ File.separator +
        String doc = OS + File.separator + "argox" + File.separator + "DATA_AQUI" + "_cup_" + lista + ".txt";
        return doc;
    }

    //metodo responsavel 
    public static void enviarImpressora(String doc, String caminho) {
        try {
            String caminhoImpressora = caminho;

            java.io.InputStream is = new FileInputStream(doc);//arquivo de impressao
            Scanner sc = new Scanner(is);
            FileOutputStream fs = new FileOutputStream(caminhoImpressora);//caminho da impressora
            PrintStream ps = new PrintStream(fs);
            int cout = 0;

            while (sc.hasNextLine()) {
                System.out.println(" " + cout++);
                String linhas = sc.nextLine();
                ps.println(linhas);

            }
            sc.close();
            fs.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

//metodo responsavel por identificar o sistema operacional e a unidade    
    public static String getOS(String usuario) {
        String os = System.getProperty("os.name");
        String Sistema = "C:";
        String user = usuario;

        try {

            if (os.startsWith("Windows")) {
                Sistema = "C:";
            } else if (os.startsWith("Linux")) {
                Sistema = "//home" + File.separator + user;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O.S não identificado");
        }

        return Sistema;
    }

}
