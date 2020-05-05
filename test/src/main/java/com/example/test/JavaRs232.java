package com.example.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JavaRs232 extends JFrame implements ActionListener, SerialPortEventListener {

    /**
     * JDK Serial Version UID
     */
    private static final long serialVersionUID = -7270865686330790103L;

    protected int WIN_WIDTH = 380;
    
    protected int WIN_HEIGHT = 300;
    
    private JComboBox<?> portCombox, rateCombox, dataCombox, stopCombox, parityCombox; 
    
    private Button openPortBtn, closePortBtn, sendMsgBtn;
    
    private TextField sendTf;
    
    private TextArea readTa;
    
    private JLabel statusLb;
    
    private String portname, rate, data, stop, parity;
    
    protected CommPortIdentifier portId;
    
    protected Enumeration<?> ports;
    
    protected List<String> portList;

    protected SerialPort serialPort;

    protected OutputStream outputStream = null; 

protected InputStream inputStream = null; 
    
protected String mesg;
    
protected int sendCount, reciveCount;
    
    /**
     * Ĭ�Ϲ��캯��
     */
    public JavaRs232() {        
        super("Java RS-232����ͨ�Ų��Գ���   �����ǳ�");
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo(null);
//        Image icon = null;
//        try {
//            icon = ImageIO.read(JavaRs232.class.getResourceAsStream("/res/rs232.png"));
//        } catch (IOException e) {
//            showErrMesgbox(e.getMessage());
//        }
//        setIconImage(icon);
        setResizable(false);
        scanPorts();
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * ��ʼ����UI���
     * @since 2012-3-22 ����11:56:39
     */
    public void initComponents() {        
        // ���ó���
        Font lbFont = new Font("΢���ź�", Font.TRUETYPE_FONT, 14);

        // ����������
        JPanel northPane = new JPanel();
        northPane.setLayout(new GridLayout(1, 1));
        // ��������������
        JPanel leftPane = new JPanel();        
        leftPane.setOpaque(false);
        leftPane.setLayout(new GridLayout(3,2));
        JLabel portnameLb = new JLabel("���ںţ�");
        portnameLb.setFont(lbFont);
        portnameLb.setHorizontalAlignment(SwingConstants.RIGHT);
        portCombox = new JComboBox<String>((String [])portList.toArray(new String[0]));
        portCombox.addActionListener(this);
        JLabel databitsLb = new JLabel("����λ��");
        databitsLb.setFont(lbFont);
        databitsLb.setHorizontalAlignment(SwingConstants.RIGHT);
        dataCombox = new JComboBox<Integer>(new Integer[]{5, 6, 7, 8});
        dataCombox.setSelectedIndex(3);
        dataCombox.addActionListener(this);
        JLabel parityLb = new JLabel("У��λ��");
        parityLb.setFont(lbFont);
        parityLb.setHorizontalAlignment(SwingConstants.RIGHT);
        parityCombox = new JComboBox<String>(new String[]{"NONE","ODD","EVEN","MARK","SPACE"});
        parityCombox.addActionListener(this);
        // �����������
        leftPane.add(portnameLb);
        leftPane.add(portCombox);
        leftPane.add(databitsLb);
        leftPane.add(dataCombox);
        leftPane.add(parityLb);
        leftPane.add(parityCombox);

        //�����ұ����
        JPanel rightPane = new JPanel();
        rightPane.setLayout(new GridLayout(3,2));
        // �����ұ��������
        JLabel baudrateLb = new JLabel("�����ʣ�");
        baudrateLb.setFont(lbFont);
        baudrateLb.setHorizontalAlignment(SwingConstants.RIGHT);
        rateCombox = new JComboBox<Integer>(new Integer[]{2400,4800,9600,14400,19200,38400,56000});
        rateCombox.setSelectedIndex(2);
        rateCombox.addActionListener(this);
        JLabel stopbitsLb = new JLabel("ֹͣλ��");
        stopbitsLb.setFont(lbFont);
        stopbitsLb.setHorizontalAlignment(SwingConstants.RIGHT);
        stopCombox = new JComboBox<String>(new String[]{"1","2","1.5"});
        stopCombox.addActionListener(this);
        openPortBtn = new Button("�򿪶˿�");
        openPortBtn.addActionListener(this);
        closePortBtn = new Button("�رն˿�");    
        closePortBtn.addActionListener(this);
        // �����������
        rightPane.add(baudrateLb);
        rightPane.add(rateCombox);
        rightPane.add(stopbitsLb);
        rightPane.add(stopCombox);
        rightPane.add(openPortBtn);
        rightPane.add(closePortBtn);
        // ��������������ӵ����ߵ����
        northPane.add(leftPane);
        northPane.add(rightPane);

        // �����м����
        JPanel centerPane = new JPanel();
        // �����м��������
        sendTf = new TextField(42);
        readTa = new TextArea(8,50);
        readTa.setEditable(false);
        readTa.setBackground(new Color(225,242,250));
        centerPane.add(sendTf);
        sendMsgBtn = new Button(" ���� ");
        sendMsgBtn.addActionListener(this);
        // �����������
        centerPane.add(sendTf);
        centerPane.add(sendMsgBtn);
        centerPane.add(readTa);
        
        // �����ϱ����
        statusLb = new JLabel();
        statusLb.setText(initStatus());
        statusLb.setOpaque(true);
        
        // ��ȡ�����������,��������������Ա����С��ϵĲ�������
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setOpaque(false);
        contentPane.add(northPane, BorderLayout.NORTH);
        contentPane.add(centerPane, BorderLayout.CENTER);
        contentPane.add(statusLb, BorderLayout.SOUTH);
    }
    
    /**
     * ��ʼ��״̬��ǩ��ʾ�ı�
     * @return String
     * @since 2012-3-23 ����12:01:53
     */
    public String initStatus() {
        portname = portCombox.getSelectedItem().toString();
        rate = rateCombox.getSelectedItem().toString();
        data = dataCombox.getSelectedItem().toString();
        stop = stopCombox.getSelectedItem().toString();
        parity = parityCombox.getSelectedItem().toString();
        
        StringBuffer str = new StringBuffer("��ǰ���ں�:");
        str.append(portname).append(" ������:");
        str.append(rate).append(" ����λ:");
        str.append(data).append(" ֹͣλ:");
        str.append(stop).append(" У��λ:");
        str.append(parity);
        return str.toString();
    }
    
    /**
     * ɨ�豾��������COM�˿�
     * @since 2012-3-23 ����12:02:42
     */
    public void scanPorts() {
        portList = new ArrayList<String>();
        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        while(en.hasMoreElements()){
            portId = (CommPortIdentifier) en.nextElement();
            if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
                String name = portId.getName();
                if(!portList.contains(name)) {
                    portList.add(name);
                }
            }
        }

        if(null == portList
                || portList.isEmpty()) {
            showErrMesgbox("δ�ҵ����õĴ��ж˿ں�,�����޷�����!");
            System.exit(0);
        }
    }
    
    /**
     * �򿪴��ж˿�
     * @since 2012-3-23 ����12:03:07
     */
    public void openSerialPort() { 
        // ��ȡҪ�򿪵Ķ˿�
        try {
            portId = CommPortIdentifier.getPortIdentifier(portname);
        } catch (NoSuchPortException e) {
            showErrMesgbox("��Ǹ,û���ҵ�"+portname+"���ж˿ں�!");
            setComponentsEnabled(true);
            return ;
        }
        // �򿪶˿�
        try {
            serialPort = (SerialPort) portId.open("JavaRs232", 2000);
            statusLb.setText(portname+"�����Ѿ���!");
        } catch (PortInUseException e) {
            showErrMesgbox(portname+"�˿��ѱ�ռ��,����!");
            setComponentsEnabled(true);
            return ;
        }
        
        // ���ö˿ڲ���
        try {
            int rate = Integer.parseInt(this.rate);
            int data = Integer.parseInt(this.data);
            int stop = stopCombox.getSelectedIndex()+1;
            int parity = parityCombox.getSelectedIndex();
            serialPort.setSerialPortParams(rate,data,stop,parity);
        } catch (UnsupportedCommOperationException e) {
            showErrMesgbox(e.getMessage());
        }

        // �򿪶˿ڵ�IO���ܵ�
        try { 
            outputStream = serialPort.getOutputStream(); 
            inputStream = serialPort.getInputStream(); 
        } catch (IOException e) {
            showErrMesgbox(e.getMessage());
        } 

        // ���˿���Ӽ�����
        try { 
            serialPort.addEventListener(this); 
        } catch (TooManyListenersException e) {
            showErrMesgbox(e.getMessage());
        } 

        serialPort.notifyOnDataAvailable(true); 
    } 
    
    /**
     * �����ж˿ڷ�������
     * @since 2012-3-23 ����12:05:00
     */
    public void sendDataToSeriaPort() { 
        try { 
            sendCount++;
            outputStream.write(mesg.getBytes()); 
            outputStream.flush(); 

        } catch (IOException e) { 
            showErrMesgbox(e.getMessage());
        } 
        
        statusLb.setText("  ����: "+sendCount+"                                      ����: "+reciveCount);
    } 
    
    /**
     * �رմ��ж˿�
     * @since 2012-3-23 ����12:05:28
     */
    public void closeSerialPort() { 
        try { 
            if(outputStream != null)
                outputStream.close();
            if(serialPort != null)
                serialPort.close(); 
            serialPort = null;
            statusLb.setText(portname+"�����Ѿ��ر�!");
            sendCount = 0;
            reciveCount = 0;
            sendTf.setText("");
            readTa.setText("");
        } catch (Exception e) { 
            showErrMesgbox(e.getMessage());
        } 
    }     
    
    /**
     * ��ʾ����򾯸���Ϣ
     * @param msg ��Ϣ
     * @since 2012-3-23 ����12:05:47
     */
    public void showErrMesgbox(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /**
     * �������Ϊ�¼�����
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == portCombox
                || e.getSource() == rateCombox
                || e.getSource() == dataCombox
                || e.getSource() == stopCombox
                || e.getSource() == parityCombox){
            statusLb.setText(initStatus());
        }
        if(e.getSource() == openPortBtn){
            setComponentsEnabled(false);            
            openSerialPort();
        }
        if(e.getSource() == closePortBtn){
            if(serialPort != null){
                closeSerialPort();
            }
            setComponentsEnabled(true);
        }
        
        if(e.getSource() == sendMsgBtn){
            if(serialPort == null){
                showErrMesgbox("���ȴ򿪴��ж˿�!");
                return ;
            }
            mesg = sendTf.getText();
            if(null == mesg || mesg.isEmpty()){
                showErrMesgbox("��������Ҫ���͵�����!");
                return ;
            }
            sendDataToSeriaPort();
        }
    }

    /**
     * �˿��¼�����
     */
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[50];

            try {
                while (inputStream.available() > 0) {
                    inputStream.read(readBuffer);
                }
                StringBuilder receivedMsg = new StringBuilder("/-- ");
                receivedMsg.append(new String(readBuffer).trim()).append(" --/\n");
                readTa.append(receivedMsg.toString());
                reciveCount++;
                statusLb.setText("  ����: "+sendCount+"                                      ����: "+reciveCount);
            } catch (IOException e) {
                showErrMesgbox(e.getMessage());
            }
        }
    }
    
    /**
     * ���ø�����Ŀ���״̬
     * @param enabled ״̬
     * @since 2012-3-23 ����12:06:24
     */
    public void setComponentsEnabled(boolean enabled) {
        openPortBtn.setEnabled(enabled);
        openPortBtn.setEnabled(enabled);
        portCombox.setEnabled(enabled);
        rateCombox.setEnabled(enabled);
        dataCombox.setEnabled(enabled);
        stopCombox.setEnabled(enabled);
        parityCombox.setEnabled(enabled);
    }
    
    /**
     * ����������
     * @param args
     * @since 2012-3-23 ����12:06:45
     */
    public static void main(String[] args) {
        new JavaRs232();        
    }
}