package Controller;

import DAO.InstrumentoDAO;
import DTO.InstrumentoDTO;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InstrumentoController {

    public static boolean inserirInstrumento(String nome, String familia, String modelo, String marca, String cor) {

        try {
            InstrumentoDTO iDTO = new InstrumentoDTO();
            InstrumentoDAO iDAO = new InstrumentoDAO();

            iDTO.setNome(nome);
            iDTO.setFamilia(familia);
            iDTO.setModelo(modelo);
            iDTO.setMarca(marca);
            iDTO.setCor(cor);

            iDAO.cadastarInstrumento(iDTO);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static javax.swing.JTable listarInstrumentos(javax.swing.JTable tbl) {
        InstrumentoDAO iDAO = new InstrumentoDAO();
        ArrayList<InstrumentoDTO> lista = iDAO.mostrarInstrumentos();

        DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
        modelo.setNumRows(0);

        for (int n = 0; n < lista.size(); n++) {
            modelo.addRow(new Object[]{
                lista.get(n).getId(),
                lista.get(n).getNome(),
                lista.get(n).getModelo(),
                lista.get(n).getFamilia(),
                lista.get(n).getMarca(),
                lista.get(n).getCor()
            });
        }
        return tbl;
    }

    public static boolean alterarInstrumento(int id, String nome, String modelo, String familia, String marca, String cor) {
        InstrumentoDAO iDAO = new InstrumentoDAO();
        InstrumentoDTO iDTO = new InstrumentoDTO();

        try {
            iDTO.setId(id);
            iDTO.setNome(nome);
            iDTO.setModelo(modelo);
            iDTO.setFamilia(familia);
            iDTO.setMarca(marca);
            iDTO.setCor(cor);

            iDAO.atualizarInstrumento(iDTO);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean apagarInstrumento(int id) {
        try {
            InstrumentoDAO iDAO = new InstrumentoDAO();
            iDAO.deletarInstrumento(id);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static InstrumentoDTO tblInstrumentoMouseClicked(java.awt.event.MouseEvent evt, javax.swing.JTable tbl) {
        int indexLinha = tbl.getSelectedRow();

        if (indexLinha != -1) {
            int id = stringParaInt(tbl.getValueAt(indexLinha, 0).toString());
            String nome = tbl.getValueAt(indexLinha, 1).toString();
            String modelo = tbl.getValueAt(indexLinha, 2).toString();
            String familia = tbl.getValueAt(indexLinha, 3).toString();
            String marca = tbl.getValueAt(indexLinha, 4).toString();
            String cor = tbl.getValueAt(indexLinha, 5).toString();

            InstrumentoDTO iDTO = new InstrumentoDTO();
            iDTO.setId(id);
            iDTO.setNome(nome);
            iDTO.setModelo(modelo);
            iDTO.setFamilia(familia);
            iDTO.setMarca(marca);
            iDTO.setCor(cor);

            return iDTO;
        } else {
            return null;
        }
    }

    public static int stringParaInt(String num) {
        try {
            int convertido = Integer.parseInt(num);
            return convertido;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter string para int: " + ex);
            return 0;
        }
    }

}
