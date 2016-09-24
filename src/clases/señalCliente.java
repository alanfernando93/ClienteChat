/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import vista.chatIndividual;
import vista.ventanaChat;

/**
 *
 * @author Huayllani
 */
public class señalCliente extends Thread {

    private ventanaChat parent; 
    private ListModel lista;
    private Vector <String> listNick;
    
    public señalCliente(ventanaChat p){
        this.parent = p;
    }
    @Override
    public void run() {
        paquete recibido,enviar;
        String id,msg,nick,ipDestino,descripcion;
        try {
            ServerSocket puente9090 = new ServerSocket(9090);
            while(true){
                listNick = new Vector();
                Socket conexion = puente9090.accept();
                ObjectInputStream capturarObjeto = new ObjectInputStream(conexion.getInputStream());
                recibido = (paquete) capturarObjeto.readObject();
                nick = recibido.getNick();
                ipDestino = recibido.getIpDestino();
                msg = recibido.getMsg();
                descripcion = recibido.getDescripcion();
                id = recibido.getId();
                //JOptionPane.showMessageDialog(null, "--> "+nick+"\n,"+parent.getNick()+"\n,"+ parent.getLista().get(nick));
                switch (descripcion){
                    case "chatIndividual":
                        if(parent.listaVentatasChats.containsKey(id)){
                            chatIndividual abrir = parent.listaVentatasChats.get(id);
                            abrir.setVisible(true);
                        }else{                            
                            chatIndividual chat = new chatIndividual();
                            chat.setIpServer(parent.getIpServer());
                            chat.setId(id);
                            chat.setNickLocal(parent.getNick());
                            chat.setNickChat(nick);
                            chat.setIpDestino(parent.getLista().get(nick));
                            chat.setTitle(nick + " - Chat");
                            chat.show();
                            parent.listaVentatasChats.put(id, chat);
                        }
                        chatIndividual.campoChat.append(nick + " : " + msg + "\n");
                        break;
                    case "listaIps":
                        int i = 0;
                        parent.setLista(recibido.getListIps());
                        Iterator it = recibido.getListIps().keySet().iterator();
                        parent.listaChatConectados.removeAll();

                        while(it.hasNext()){
                            String key = (String) it.next();

                            if(!key.equals(parent.getNick())){                        
                                listNick.add(key);       
                                if(i == recibido.getListIps().size()-1){
                                    parent.campoChat.append(recibido.getNick() + " : Conectado\n");
                                }
                            }
                            ++i;
                        }
                        parent.listaChatConectados.setListData(listNick);
                        break;
                    case "ChatGrupal":
                        parent.campoChat.append(recibido.getNick() + " : " + recibido.getMsg() + "\n");
                        break;
                }
                
                capturarObjeto.close();
                conexion.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(señalCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
