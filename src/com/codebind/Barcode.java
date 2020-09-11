package com.codebind;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.UIManager;

public class Barcode extends Component {

    private JButton openButton;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton reColorButton;
    private JButton sintral;
    private JButton gridRecolor;
    private JButton birdseyeSintralButton;
    private JButton doAGridButton;
    private JButton combine;

    final JFileChooser  fc = new JFileChooser("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/");

    final int period = new Color(255, 255, 255).getRGB();
    final int A = new Color(0, 0, 0).getRGB();
    final int Y = new Color(0, 60, 167).getRGB();
    final int T = new Color(0, 149, 55).getRGB();
    final int star = new Color(160, 72, 0).getRGB();
    final int I = new Color(255, 62, 255).getRGB();
    final int plus = new Color(198, 1, 45).getRGB();
    final int B = new Color(255, 141, 17).getRGB();
    final int G = new Color(33, 58, 5).getRGB();
    final int H = new Color(122, 174, 213).getRGB();
    final int O = new Color(245, 196, 0).getRGB();
    final int W = new Color(152, 0, 152).getRGB();
    final int Z = new Color(128, 255, 0).getRGB();
    final int X = new Color(137, 138, 142).getRGB();
    final int N = new Color(75, 8, 103).getRGB();
    final int S = new Color(167, 20, 51).getRGB();
    final int E = new Color(104, 96, 104).getRGB();
    final int K = new Color(64, 0, 56).getRGB();
    final int L = new Color(17, 27, 78).getRGB();
    final int M = new Color(2, 86, 48).getRGB();
    final int P = new Color(64, 0, 0).getRGB();
    final int Q = new Color(255, 183, 12).getRGB();
    final int a = new Color(60, 56, 52).getRGB();
    final int y = new Color(0, 136, 136).getRGB();
    final int t = new Color(40, 240, 120).getRGB();
    final int smalli = new Color(252, 218, 17).getRGB();
    final int b = new Color(196, 73, 0).getRGB();
    final int g = new Color(213, 144, 0).getRGB();
    final int h = new Color(61, 71, 19).getRGB();
    final int o = new Color(213, 199, 186).getRGB();
    final int w = new Color(249, 218, 224).getRGB();
    final int z = new Color(112, 255, 112).getRGB();
    final int smalle = new Color(144, 136, 144).getRGB();
    final int smallk = new Color(194, 148, 113).getRGB();
    final int l = new Color(108, 23, 50).getRGB();
    final int m = new Color(228, 228, 228).getRGB();
    final int p = new Color(81, 43, 28).getRGB();
    final int q = new Color(29, 61, 109).getRGB();
    boolean validFlag = true;
    boolean is8Color = false;
    boolean is7Color = false;
    boolean is6Color = false;
    boolean is5Color = false;
    boolean is4Color = false;
    boolean isDoubleProd = false;

    public Barcode() {


        String[] choices = {"53","63","90"};
        String sizeString = (String)JOptionPane.showInputDialog(null,"Choose a size","Choose a size",JOptionPane.QUESTION_MESSAGE,null,choices,choices[1]);
        int pxWidth = 0;
        if(sizeString.equals("53")){
            pxWidth = 276;
        }
        else if(sizeString.equals("63")){
            pxWidth = 340;
        }
        else if(sizeString.equals("90")){
            pxWidth = 490;
        }

        final int[] numberOfColors = {0};
        int finalPxWidth = pxWidth;
        openButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                int[] validColors = {period,A,Y,T,star,I,plus,B,G,H,O,W,Z,X,N,S,E,K,L,M,P,Q,a,y,t,smalli,b,g,h,o,w,z,smalle,smallk,l,m,p,q};

                if (e.getSource() == openButton) {
                    int returnVal = fc.showOpenDialog(Barcode.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();

                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(new File(String.valueOf(file)));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        image = image.getSubimage(5, 5, 473, finalPxWidth);
                        for (int i = 0; i < 473; i++) {
                            for (int j = 0; j < (finalPxWidth/2); j++) {
                                int tmp = image.getRGB(i, j);
                                image.setRGB(i, j, image.getRGB(i, image.getHeight() - j - 1));
                                image.setRGB(i, image.getHeight() - j - 1, tmp);
                            }
                        }



                        String filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/currentBMP.bmp";
                        File outputfile = new File(filename);
                        try {
                            ImageIO.write(image, "bmp", outputfile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }


                        Set<Integer> colors = new HashSet<Integer>();
                        int w = image.getWidth();
                        int h = image.getHeight();
                        for(int y = 0; y < h; y++) {
                            for(int x = 0; x < w; x++) {
                                int pixel = image.getRGB(x, y);
                                colors.add(pixel);
                                int currentColor = new Color(image.getRGB(x, y)).getRGB();
                                if(Arrays.asList(validColors).contains(currentColor)!= true){
                                    validFlag = false;
                                }
                            }
                        }
                        numberOfColors[0] = colors.size();
                    }


                }


            }


        });



        int num = Integer.parseInt(JOptionPane.showInputDialog(null, "How many colors are there?"));
        int[] finalColor = {period, A, Y, T, star, I, plus, B};




        reColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(num!= numberOfColors[0]){
                    JOptionPane.showMessageDialog(null,"The number of colors don't match...");
                }

             /*   if(validFlag == false){
                    JOptionPane.showMessageDialog(null,"There's an invalid color somewhere.");
                }  */

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/currentBMP.bmp"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                String[] color = new String[8];

                color[0] = textField1.getText();
                color[1] = textField2.getText();
                color[2] = textField3.getText();

                if (textField4.getText().isEmpty() != true) {
                    color[3] = textField4.getText();
                } else {
                    color[3] = " ";
                }

                if (textField5.getText().isEmpty() != true) {
                    color[4] = textField5.getText();
                } else {
                    color[4] = " ";
                }

                if (textField6.getText().isEmpty() != true) {
                    color[5] = textField6.getText();
                } else {
                    color[5] = " ";
                }

                if (textField7.getText().isEmpty() != true) {
                    color[6] = textField7.getText();
                } else {
                    color[6] = " ";
                }

                if (textField8.getText().isEmpty() != true) {
                    color[7] = textField8.getText();
                } else {
                    color[7] = " ";
                }

                int[] rgb = new int[num];

                for (int i = 0; i < num; i++) {

                    switch (color[i]) {
                        case ".":
                            rgb[i] = period;
                            break;
                        case "A":
                            rgb[i] = A;
                            break;
                        case "Y":
                            rgb[i] = Y;
                            break;
                        case "T":
                            rgb[i] = T;
                            break;
                        case "*":
                            rgb[i] = star;
                            break;
                        case "I":
                            rgb[i] = I;
                            break;
                        case "+":
                            rgb[i] = plus;
                            break;
                        case "B":
                            rgb[i] = B;
                            break;
                        case "G":
                            rgb[i] = G;
                            break;
                        case "H":
                            rgb[i] = H;
                            break;
                        case "O":
                            rgb[i] = O;
                            break;
                        case "W":
                            rgb[i] = W;
                            break;
                        case "Z":
                            rgb[i] = Z;
                            break;
                        case "X":
                            rgb[i] = X;
                            break;
                        case "N":
                            rgb[i] = N;
                            break;
                        case "S":
                            rgb[i] = S;
                            break;
                        case "E":
                            rgb[i] = E;
                            break;
                        case "K":
                            rgb[i] = K;
                            break;
                        case "L":
                            rgb[i] = L;
                            break;
                        case "M":
                            rgb[i] = M;
                            break;
                        case "P":
                            rgb[i] = P;
                            break;
                        case "Q":
                            rgb[i] = Q;
                            break;
                        case "a":
                            rgb[i] = a;
                            break;
                        case "y":
                            rgb[i] = y;
                            break;
                        case "t":
                            rgb[i] = t;
                            break;
                        case "i":
                            rgb[i] = smalli;
                            break;
                        case "b":
                            rgb[i] = b;
                            break;
                        case "g":
                            rgb[i] = g;
                            break;
                        case "h":
                            rgb[i] = h;
                            break;
                        case "o":
                            rgb[i] = o;
                            break;
                        case "w":
                            rgb[i] = w;
                            break;
                        case "z":
                            rgb[i] = z;
                            break;
                        case "e":
                            rgb[i] = smalle;
                            break;
                        case "k":
                            rgb[i] = smallk;
                            break;
                        case "l":
                            rgb[i] = l;
                            break;
                        case "m":
                            rgb[i] = m;
                            break;
                        case "p":
                            rgb[i] = p;
                            break;
                        case "q":
                            rgb[i] = q;
                            break;
                        default:
                            //  throw new IllegalArgumentException("Invalid color: " + rgb[i]);
                    }
                }

                int[] funky = new int[8];
                funky[0] = new Color(10, 10, 10).getRGB();
                funky[1] = new Color(40, 40, 40).getRGB();
                funky[2] = new Color(80, 80, 80).getRGB();
                funky[3] = new Color(255, 255, 251).getRGB();
                funky[4] = new Color(255, 255, 250).getRGB();
                funky[5] = new Color(255, 255, 249).getRGB();
                funky[6] = new Color(255, 255, 248).getRGB();
                funky[7] = new Color(255, 255, 247).getRGB();

//System.out.println(color[4]);
                for (int x = 0; x < 473; x++) {
                    for (int y1 = 0; y1 < finalPxWidth; y1++) {
                        int colorIndex = 0;
                        while (colorIndex < num) {
                            int current = image.getRGB(x, y1);
                            if (current == rgb[colorIndex]) {
                                image.setRGB(x, y1, funky[colorIndex]);
                            }
                            colorIndex++;
                        }

                    }
                }

                for (int x = 0; x < 473; x++) {
                    for (int y1 = 0; y1 < finalPxWidth; y1++) {
                        int colorIndex = 0;
                        while (colorIndex < num) {
                            int current = image.getRGB(x, y1);
                            if (current == funky[colorIndex]) {
                                image.setRGB(x, y1, finalColor[colorIndex]);
                            }
                            colorIndex++;
                        }

                    }
                }

                String filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/currentBMP.bmp";
                File outputfile = new File(filename);
                try {
                    ImageIO.write(image, "bmp", outputfile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                //This section adds the Barcode

                image = null;
                try {
                    image = ImageIO.read(new File("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/currentBMP.bmp"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                BufferedImage biggerImg = new BufferedImage(481, finalPxWidth, BufferedImage.TYPE_INT_ARGB);
                BufferedImage blank = new BufferedImage(8, finalPxWidth, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = biggerImg.createGraphics();

                ArrayList<Integer>[] barcodeArray = new ArrayList[finalPxWidth];

                for (int rows = 0; rows < finalPxWidth; rows++) {
                    for (int cols = 0; cols < 8; cols++) {
                        blank.setRGB(cols, rows, period);
                    }
                }

                g2d.drawImage(blank, 0, 0, null);
                g2d.drawImage(image, 8, 0, null);

                Boolean[] colorFlags = new Boolean[8];
                int[] colorsPerRow = {0, 0, 0, 0, 0, 0, 0, 0};
                for (int i = 0; i < finalPxWidth; i++) {
                    Arrays.fill(colorFlags, Boolean.FALSE);
                    for (int j = 8; j < 481; j++) {
                        int current = biggerImg.getRGB(j, i);
                        if (current == period) {
                            colorFlags[0] = true;
                        } else if (current == A) {
                            colorFlags[1] = true;
                        } else if (current == Y) {
                            colorFlags[2] = true;
                        } else if (current == T) {
                            colorFlags[3] = true;
                        } else if (current == star) {
                            colorFlags[4] = true;
                        } else if (current == I) {
                            colorFlags[5] = true;
                        } else if (current == plus) {
                            colorFlags[6] = true;
                        } else if (current == B) {
                            colorFlags[7] = true;
                        }
                    }

                    barcodeArray[i] = new ArrayList<Integer>();

                    int rowCount = 0;
                    int k;
                    int maxC = 0;
                    for (k = 0; k < 8; k++) {
                        if (colorFlags[k] == true) {
                            biggerImg.setRGB(rowCount, i, finalColor[k]);
                            barcodeArray[i].add(finalColor[k]);
                            //   biggerImg.setRGB(rowCount+8,i,finalColor[k]);
                            rowCount++;
                        }

                    }


                }

                int i;
                int numLines[] = {0, 0, 1, 0, 0, 0, 0, 0};
                for(i=1;i<(finalPxWidth-1);i++){


                    switch(barcodeArray[i].size()) {
                        case 3:
                            numLines[2]++;
                            break;
                        case 4:
                            numLines[3]++;
                            break;
                        case 5:
                            numLines[4]++;
                            break;
                        case 6:
                            numLines[5]++;
                            break;
                        case 7:
                            numLines[6]++;
                            break;
                        case 8:
                            numLines[7]++;
                            break;
                    }

                    if(barcodeArray[i].size()<3){
                        numLines[2]++;
                        if (barcodeArray[i].contains(finalColor[3])||barcodeArray[i].contains(finalColor[4]) || barcodeArray[i].contains(finalColor[5]) || barcodeArray[i].contains(finalColor[6])||barcodeArray[i].contains(finalColor[7])){
                            JOptionPane.showMessageDialog(null,"Minimum Color Alert!!!");
                        }

                        int colors = barcodeArray[i].size();

                        if(colors==1){
                            barcodeArray[i].set(0,finalColor[0]);
                            barcodeArray[i].add(finalColor[1]);
                            barcodeArray[i].add(finalColor[2]);
                        }
                        else if(colors==2){
                            barcodeArray[i].set(0,finalColor[0]);
                            barcodeArray[i].set(1,finalColor[1]);
                            barcodeArray[i].add(finalColor[2]);

                        }
                    }

                    if((barcodeArray[i].equals(barcodeArray[i+1]))==false && (i%2==0) && (barcodeArray[i].size() < barcodeArray[i+1].size())){
                        barcodeArray[i]=barcodeArray[i+1];
                        System.out.println(i);
                    }

                    else if((barcodeArray[i].equals(barcodeArray[i+1]))==false && (i%2==0) && (barcodeArray[i].size() > barcodeArray[i+1].size())){
                        barcodeArray[i+1]=barcodeArray[i];
                        System.out.println(i);
                        i++;

                    }


                }

                for (i = 0; i < finalPxWidth; i++) {
                    int c;
                    for (c = 0; c < barcodeArray[i].size(); c++) {
                        biggerImg.setRGB(c, i, barcodeArray[i].get(c));
                    }
                }

                int total = numLines[2]+numLines[3]+numLines[4]+numLines[5]+numLines[6]+numLines[7];
                double adjust = numLines[2]+(numLines[3]*1.1)+(numLines[4]*1.2)+(numLines[5]*1.27)+(numLines[6]*1.31)+(numLines[7]*1.31);
                double birdseyeadjust = numLines[2]+(numLines[3]*1.031)+(numLines[4]*1.1)+(numLines[5]*1.222)+(numLines[6]*1.294)+(numLines[7]*1.32);
                double difference = adjust - total;
                double birdseyedifference = birdseyeadjust - total;
                double tcTime=(numLines[2]*2.5+numLines[3]*5+numLines[4]*5+numLines[5]*5+numLines[6]*13+numLines[7]*13)/60;
                double notTC=(numLines[2]*5+numLines[3]*5+numLines[4]*13.8+numLines[5]*13.8+numLines[6]*13.8+numLines[7]*13.8)/60;;
                JOptionPane.showMessageDialog(null,"3: "+numLines[2]+"\n4: "+ numLines[3] + "\n5: "+numLines[4]+"\n6: "+numLines[5]+"\n7: "+numLines[6]+"\n8: "+numLines[7]+"\nTotal Lines Counted: "+total+"\nDelete this many(regular): "+difference+"\nDelete this many(BIRDSEYE): "+birdseyedifference+"\nTime on TC: "+tcTime+" minutes\nTime on 440: " + notTC + " minutes");


                BufferedImage finalImage = new BufferedImage(biggerImg.getWidth(),
                        biggerImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                finalImage.createGraphics().drawImage(biggerImg, 0, 0, Color.WHITE, null);
                filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/barcoded.bmp";
                outputfile = new File(filename);
                try {
                    ImageIO.write(finalImage, "bmp", outputfile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                //This section converts the bitmap to Birdseye

                image = null;
                try {
                    image = ImageIO.read(new File("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/barcoded.bmp"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                ArrayList<Integer> colors = new ArrayList<>(8);
                colors.add(period);
                colors.add(A);
                colors.add(Y);
                colors.add(T);
                colors.add(star);
                colors.add(I);
                colors.add(plus);
                colors.add(B);

                int[] finalColor = {G,H,O,W,Z,E,K,L};

                ArrayList<Integer> offsets = new ArrayList<>(8);
                offsets.add(G);
                offsets.add(H);
                offsets.add(O);
                offsets.add(W);
                offsets.add(Z);
                offsets.add(E);
                offsets.add(K);
                offsets.add(L);

                int w = image.getWidth();
                int h = image.getHeight();


                for (i = 8; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        //  System.out.println(i);
                        // System.out.println(j);
                        int current = image.getRGB(i, j);
                        int index = colors.indexOf(current);
                        //System.out.println(index);
                        if(i==0||i%2==0){
                            if(j==1||j%2!=0) {
                                image.setRGB(i, j, offsets.get(index));
                            }
                        }

                        if(i==1||i%2!=0){
                            if(j==0||j%2==0)
                                image.setRGB(i,j,offsets.get(index));
                        }

                    }
                }

                image.setRGB(0,h-1,X);

                filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Birdseye.bmp";
                outputfile = new File(filename);
                try {
                    ImageIO.write(image, "bmp", outputfile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });



        sintral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isX = false;


                if (e.getSource() == sintral) {
                    int returnVal = fc.showOpenDialog(Barcode.this);
                    File file = null;
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                    }
                    String inputName = JOptionPane.showInputDialog(null,"Name your file.");
                    String filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/"+inputName+".txt";
                    File sintralNew = new File(filename);
                    FileReader fileReader = null;
                    FileReader templateReader = null;
                    ArrayList<String> colors = new ArrayList<>(8);
                    colors.add(".");
                    colors.add("A");
                    colors.add("Y");
                    colors.add("T");
                    colors.add("*");
                    colors.add("I");
                    colors.add("+");
                    colors.add("B");
                    ArrayList<String> carriers = new ArrayList<>(8);
                    carriers.add("2");
                    carriers.add("3");
                    carriers.add("4");
                    carriers.add("5");
                    carriers.add("6");
                    carriers.add("7");
                    carriers.add("1");
                    carriers.add("8");
                    try {

                        String threeColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(21);
                        String threeColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(22);
                        String threeColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(24);
                        String threeColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(25);
                        String fourColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(28);
                        String fourColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(29);
                        String fiveColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(32);
                        String fiveColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(33);
                        String fiveColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(34);
                        String fiveColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(35);
                        String fiveColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(36);
                        String fiveColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(37);
                        String fiveColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(40);
                        String fiveColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(41);
                        String sixColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(44);
                        String sixColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(45);
                        String sixColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(46);
                        String sixColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(47);
                        String sixColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(48);
                        String sixColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(49);
                        String sixColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(52);
                        String sixColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(53);
                        String sevenColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(56);
                        String sevenColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(57);
                        String sevenColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(58);
                        String sevenColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(59);
                        String sevenColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(60);
                        String sevenColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(61);
                        String eightColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(64);
                        String eightColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(65);
                        String eightColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(66);
                        String eightColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(67);
                        String eightColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(68);
                        String eightColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(69);
                      //  System.out.println(eightColorA);
                        String tcPersA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(160);
                        String tcPersB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(161);

                        String JA2Reps = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(151);

                        fileReader = new FileReader(file);
                   //     templateReader = new FileReader("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        //  int colorsOnLines[] = {0, 0, 0, 0, 0, 0, 0, 0};
                        int numColors=0;
                        int lastNumColors;
                        String current;
                        int repeats=0;
                        String lastLine;
                        String fullLine = "";
                        ArrayList<String> list = new ArrayList<String>();
                        int lineNumber = -1;

                        FileWriter writer = new FileWriter(sintralNew,true);

                        for(int templateLine = 0;templateLine<19;templateLine++){
                            String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(templateLine);
                            writer.write(currentTemplateLine);
                            writer.write(System.getProperty( "line.separator" ));
                        }
                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(164));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(165));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(164));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(165));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.flush();
                        writer.close();

                        while ((line = bufferedReader.readLine()) != null) {
                            lineNumber++;
                            lastLine = fullLine;
                            fullLine = "";
                            lastNumColors=numColors;
                            numColors = 2;

                            if(String.valueOf(line.charAt(5)).equals("$")){
                                fullLine=lastLine;
                                repeats--;
                            }

                            else if (String.valueOf(line.charAt(5)).equals(".")||String.valueOf(line.charAt(5)).equals("X")||String.valueOf(line.charAt(5)).equals("z")||String.valueOf(line.charAt(5)).equals("A")||String.valueOf(line.charAt(5)).equals("Y")||String.valueOf(line.charAt(5)).equals("T")||String.valueOf(line.charAt(5)).equals("i")){
                                fullLine += String.valueOf(line.charAt(5));
                                fullLine += String.valueOf(line.charAt(6));
                                fullLine += String.valueOf(line.charAt(7));
                                numColors++;
                                current = String.valueOf(line.charAt(8));
                               // System.out.println(fullLine);
                                if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                    numColors++;
                                    fullLine += current;
                                    current = String.valueOf(line.charAt(9));

                                    if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                        numColors++;
                                        fullLine += current;
                                        current = String.valueOf(line.charAt(10));
                                        // System.out.println(current);
                                        if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")){
                                            numColors++;
                                            fullLine += current;
                                            current = String.valueOf(line.charAt(11));
                                            //   System.out.println(current);
                                            if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")){
                                                numColors++;
                                                fullLine += current;
                                                current = String.valueOf(line.charAt(12));
                                                //  System.out.println(current);
                                                if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")){
                                                    numColors++;
                                                    fullLine += current;
                                                }
                                            }
                                        }
                                    }
                                }
                            }





                             if(lineNumber>0 && lastLine.equals(fullLine)==false && repeats>1){

                                if(repeats%2!=0 && !String.valueOf(fullLine.charAt(0)).equals("X")){
                                    JOptionPane.showMessageDialog(null,"Oh shit...I think the barcode is wrong.");
                                    System.out.println(repeats);
                                    return;
                                }

                                if (lastLine.length() == 3 && repeats>1) {
                                  //  System.out.println(repeats);
                                    char characterA = lastLine.charAt(0);
                                    char carrierA;

                                    if(String.valueOf(characterA).equals("z")||String.valueOf(characterA).equals("i")){
                                         carrierA = carriers.get(colors.indexOf(".")).charAt(0);
                                    }
                                    else{
                                        carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    }


                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);

                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC;

                                    if(String.valueOf(characterC).equals("S")){
                                        carrierC = carriers.get(colors.indexOf("Y")).charAt(0);
                                    }
                                    else {
                                        carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    }

                                    if(String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX=true;
                                        repeats += 2;
                                    }


                                    if(String.valueOf(lastLine.charAt(0)).equals("i")) {

                                        writer = new FileWriter(sintralNew, true);
                                        String repeatString = "REP*" + String.valueOf(repeats / 2);
                                        writer.write(repeatString);
                                        writer.write(System.lineSeparator());
                                        writer.write("26 <<	S:<1+>.(15)-R(16)/"+characterB +"-R/"+characterC +"-R;	Y:"+carrierA+"/"+carrierB+"/"+carrierC+";	WM=7		WMI=11	SX SX SX");
                                        writer.write(System.lineSeparator());
                                        writer.write("27 >>\tS:<1+>.-R/"+characterB +"-R/"+characterC +"-R;	Y:"+carrierA+"/"+carrierB+"/"+carrierC+";	WM=7		WMI=11	SX SX SX");
                                        writer.write(System.lineSeparator());
                                        writer.write("REPEND");
                                        writer.write(System.lineSeparator());
                                        writer.flush();
                                        writer.close();
                                    }


                                    else {
                                        StringBuilder firstLine = new StringBuilder(threeColorA);
                                        StringBuilder secondLine = new StringBuilder(threeColorB);
                                        firstLine.setCharAt(14, characterA);
                                        firstLine.setCharAt(24, characterB);
                                        firstLine.setCharAt(28, characterC);
                                        firstLine.setCharAt(36, carrierA);
                                        firstLine.setCharAt(38, carrierB);
                                        firstLine.setCharAt(40, carrierC);
                                        secondLine.setCharAt(14, characterA);
                                        secondLine.setCharAt(24, characterB);
                                        secondLine.setCharAt(28, characterC);
                                        secondLine.setCharAt(36, carrierA);
                                        secondLine.setCharAt(38, carrierB);
                                        secondLine.setCharAt(40, carrierC);


                                        String repeatString = "REP*" + String.valueOf(repeats / 2);
                                        writer = new FileWriter(sintralNew, true);
                                        writer.write(repeatString);
                                        writer.write(System.lineSeparator());
                                        writer.write(firstLine.toString());
                                        writer.write(System.lineSeparator());
                                        writer.write(secondLine.toString());
                                        writer.write(System.lineSeparator());
                                        writer.write("REPEND");
                                        writer.write(System.lineSeparator());
                                        writer.flush();
                                        writer.close();
                                    }


                                }

                                 if (lastLine.length() == 4) {

                                     is4Color = true;
                                     char characterA = lastLine.charAt(0);
                                     char carrierA;

                                     if(String.valueOf(characterA).equals("z")){
                                         carrierA = carriers.get(colors.indexOf(".")).charAt(0);
                                     }
                                     else{
                                         carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                     }

                                     char characterB = lastLine.charAt(1);
                                     char characterC = lastLine.charAt(2);
                                     char characterD = lastLine.charAt(3);
                                     char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                     char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                     char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);

                                     if(String.valueOf(fullLine.charAt(0)).equals("X")){
                                         isX=true;
                                         repeats+=2;
                                     }

                                     // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                     StringBuilder firstLine = new StringBuilder(fourColorA);
                                     StringBuilder secondLine = new StringBuilder(fourColorB);

                                     firstLine.setCharAt(14,characterA);
                                     firstLine.setCharAt(24,characterB);
                                     firstLine.setCharAt(28,characterC);
                                     firstLine.setCharAt(32,characterD);
                                     firstLine.setCharAt(39,carrierA);
                                     firstLine.setCharAt(41,carrierB);
                                     firstLine.setCharAt(43,carrierC);
                                     firstLine.setCharAt(45,carrierD);
                                     firstLine.setCharAt(45,carrierD);
                                     secondLine.setCharAt(14,characterA);
                                     secondLine.setCharAt(24,characterB);
                                     secondLine.setCharAt(28,characterC);
                                     secondLine.setCharAt(32,characterD);
                                     secondLine.setCharAt(39,carrierA);
                                     secondLine.setCharAt(41,carrierB);
                                     secondLine.setCharAt(43,carrierC);
                                     secondLine.setCharAt(45,carrierD);


                                     String repeatString = "REP*"+String.valueOf(repeats/2);
                                     writer = new FileWriter(sintralNew,true);
                                     writer.write(repeatString);
                                     writer.write(System.lineSeparator());
                                     writer.write(firstLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(secondLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write("REPEND");
                                     writer.write(System.lineSeparator());
                                     writer.flush();
                                     writer.close();
                                 }

                                 if (lastLine.length() == 5) {
                                    is5Color = true;
           //   System.out.println(repeats);
                                     char characterA = lastLine.charAt(0);
                                     char characterB = lastLine.charAt(1);
                                     char characterC = lastLine.charAt(2);
                                     char characterD = lastLine.charAt(3);
                                     char characterE = lastLine.charAt(4);
                                     char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                     char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                     char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                     char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                     char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);

                                     if(String.valueOf(fullLine.charAt(0)).equals("X")){
                                         isX=true;
                                         repeats+=2;
                                     }

                                     // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                     StringBuilder firstLine = new StringBuilder(fiveColorA);
                                     String secondLine = fiveColorB;
                                     StringBuilder thirdLine = new StringBuilder(fiveColorC);
                                     StringBuilder fourthLine = new StringBuilder(fiveColorD);
                                     String fifthLine = fiveColorE;
                                     StringBuilder sixthLine = new StringBuilder(fiveColorF);

                                     StringBuilder tcOne = new StringBuilder(fiveColorAtc);
                                     StringBuilder tcTwo = new StringBuilder(fiveColorBtc);

                                     firstLine.setCharAt(14,characterA);
                                     firstLine.setCharAt(24,characterB);
                                     firstLine.setCharAt(28,characterC);
                                     firstLine.setCharAt(32,characterD);

                                     firstLine.setCharAt(39,carrierA);
                                     firstLine.setCharAt(41,carrierB);
                                     firstLine.setCharAt(43,carrierC);
                                     firstLine.setCharAt(45,carrierD);
                                     firstLine.setCharAt(47,carrierE);

                                     tcOne.setCharAt(15,characterA);
                                     tcOne.setCharAt(25,characterB);
                                     tcOne.setCharAt(29,characterC);
                                     tcOne.setCharAt(33,characterD);
                                     tcOne.setCharAt(37,characterE);
                                     tcOne.setCharAt(44,carrierA);
                                     tcOne.setCharAt(46,carrierB);
                                     tcOne.setCharAt(48,carrierC);
                                     tcOne.setCharAt(50,carrierD);
                                     tcOne.setCharAt(52,carrierE);

                                     tcTwo.setCharAt(15,characterA);
                                     tcTwo.setCharAt(25,characterB);
                                     tcTwo.setCharAt(29,characterC);
                                     tcTwo.setCharAt(33,characterD);
                                     tcTwo.setCharAt(37,characterE);
                                     tcTwo.setCharAt(44,carrierA);
                                     tcTwo.setCharAt(46,carrierB);
                                     tcTwo.setCharAt(48,carrierC);
                                     tcTwo.setCharAt(50,carrierD);
                                     tcTwo.setCharAt(52,carrierE);

                                     thirdLine.setCharAt(10,characterE);


                                     fourthLine.setCharAt(14,characterA);
                                     fourthLine.setCharAt(24,characterB);
                                     fourthLine.setCharAt(28,characterC);
                                     fourthLine.setCharAt(32,characterD);

                                     sixthLine.setCharAt(10,characterE);




                                     String repeatString = "REP*"+String.valueOf(repeats/2);
                                     writer = new FileWriter(sintralNew,true);
                                     writer.write(repeatString);
                                     writer.write(System.lineSeparator());
                                     writer.write(firstLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(secondLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(thirdLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fourthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fifthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(sixthLine.toString());
                                     writer.write(System.lineSeparator());

                                     writer.write(tcOne.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(tcTwo.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write("REPEND");
                                     writer.write(System.lineSeparator());
                                     writer.flush();
                                     writer.close();
                                 }

                                 if (lastLine.length() == 6) {
                                    is6Color = true;

                                     char characterA = lastLine.charAt(0);
                                     char characterB = lastLine.charAt(1);
                                     char characterC = lastLine.charAt(2);
                                     char characterD = lastLine.charAt(3);
                                     char characterE = lastLine.charAt(4);
                                     char characterF = lastLine.charAt(5);
                                     char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                     char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                     char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                     char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                     char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                     char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);

                                     if(String.valueOf(fullLine.charAt(0)).equals("X")){
                                         isX=true;
                                         repeats+=2;
                                     }

                                     // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                     StringBuilder firstLine = new StringBuilder(sixColorA);
                                     String secondLine = sixColorB;
                                     StringBuilder thirdLine = new StringBuilder(sixColorC);
                                     StringBuilder fourthLine = new StringBuilder(sixColorD);
                                     String fifthLine = sixColorE;
                                     StringBuilder sixthLine = new StringBuilder(sixColorF);
                                     StringBuilder tcOne = new StringBuilder(sixColorAtc);
                                     StringBuilder tcTwo = new StringBuilder(sixColorBtc);


                                     firstLine.setCharAt(14,characterA);
                                     firstLine.setCharAt(24,characterB);
                                     firstLine.setCharAt(28,characterC);
                                     firstLine.setCharAt(32,characterD);

                                     firstLine.setCharAt(39,carrierA);
                                     firstLine.setCharAt(41,carrierB);
                                     firstLine.setCharAt(43,carrierC);
                                     firstLine.setCharAt(45,carrierD);
                                     firstLine.setCharAt(47,carrierE);
                                     firstLine.setCharAt(49,carrierF);

                                     thirdLine.setCharAt(10,characterE);
                                     thirdLine.setCharAt(14,characterF);


                                     fourthLine.setCharAt(14,characterA);
                                     fourthLine.setCharAt(24,characterB);
                                     fourthLine.setCharAt(28,characterC);
                                     fourthLine.setCharAt(32,characterD);

                                     sixthLine.setCharAt(10,characterE);
                                     sixthLine.setCharAt(14,characterF);


                                     tcOne.setCharAt(15,characterA);
                                     tcOne.setCharAt(25,characterB);
                                     tcOne.setCharAt(29,characterC);
                                     tcOne.setCharAt(33,characterD);
                                     tcOne.setCharAt(37,characterE);
                                     tcOne.setCharAt(41,characterF);
                                     tcOne.setCharAt(48,carrierA);
                                     tcOne.setCharAt(50,carrierB);
                                     tcOne.setCharAt(52,carrierC);
                                     tcOne.setCharAt(54,carrierD);
                                     tcOne.setCharAt(56,carrierE);
                                     tcOne.setCharAt(58,carrierF);

                                     tcTwo.setCharAt(15,characterA);
                                     tcTwo.setCharAt(25,characterB);
                                     tcTwo.setCharAt(29,characterC);
                                     tcTwo.setCharAt(33,characterD);
                                     tcTwo.setCharAt(37,characterE);
                                     tcTwo.setCharAt(41,characterF);
                                     tcTwo.setCharAt(48,carrierA);
                                     tcTwo.setCharAt(50,carrierB);
                                     tcTwo.setCharAt(52,carrierC);
                                     tcTwo.setCharAt(54,carrierD);
                                     tcTwo.setCharAt(56,carrierE);
                                     tcTwo.setCharAt(58,carrierF);


                                     String repeatString = "REP*"+String.valueOf(repeats/2);
                                     writer = new FileWriter(sintralNew,true);
                                     writer.write(repeatString);
                                     writer.write(System.lineSeparator());
                                     writer.write(firstLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(secondLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(thirdLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fourthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fifthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(sixthLine.toString());
                                     writer.write(System.lineSeparator());

                                     writer.write(tcOne.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(tcTwo.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write("REPEND");
                                     writer.write(System.lineSeparator());
                                     writer.flush();
                                     writer.close();
                                 }

                                 if (lastLine.length() == 7) {
                                    is7Color = true;
         // System.out.println(repeats);
                                     char characterA = lastLine.charAt(0);
                                     char characterB = lastLine.charAt(1);
                                     char characterC = lastLine.charAt(2);
                                     char characterD = lastLine.charAt(3);
                                     char characterE = lastLine.charAt(4);
                                     char characterF = lastLine.charAt(5);
                                     char characterG = lastLine.charAt(6);
                                     char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                     char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                     char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                     char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                     char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                     char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                     char carrierG = carriers.get(colors.indexOf(String.valueOf(characterG))).charAt(0);

                                     if(String.valueOf(fullLine.charAt(0)).equals("X")){
                                         isX=true;
                                         repeats+=2;
                                     }

                                     // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                     StringBuilder firstLine = new StringBuilder(sevenColorA);
                                     String secondLine = sevenColorB;
                                     StringBuilder thirdLine = new StringBuilder(sevenColorC);
                                     StringBuilder fourthLine = new StringBuilder(sevenColorD);
                                     String fifthLine = sevenColorE;
                                     StringBuilder sixthLine = new StringBuilder(sevenColorF);

                                     firstLine.setCharAt(14,characterA);
                                     firstLine.setCharAt(25,characterB);
                                     firstLine.setCharAt(29,characterC);
                                     firstLine.setCharAt(33,characterD);

                                     firstLine.setCharAt(40,carrierA);
                                     firstLine.setCharAt(42,carrierB);
                                     firstLine.setCharAt(44,carrierC);
                                     firstLine.setCharAt(46,carrierD);
                                     firstLine.setCharAt(48,carrierE);
                                     firstLine.setCharAt(50,carrierF);
                                     firstLine.setCharAt(52,carrierG);

                                     thirdLine.setCharAt(10,characterE);
                                     thirdLine.setCharAt(14,characterF);
                                     thirdLine.setCharAt(18,characterG);


                                     fourthLine.setCharAt(14,characterA);
                                     fourthLine.setCharAt(25,characterB);
                                     fourthLine.setCharAt(29,characterC);
                                     fourthLine.setCharAt(33,characterD);

                                     sixthLine.setCharAt(10,characterE);
                                     sixthLine.setCharAt(14,characterF);
                                     sixthLine.setCharAt(18,characterG);


                                     String repeatString = "REP*"+String.valueOf(repeats/2);
                                     writer = new FileWriter(sintralNew,true);
                                     writer.write(repeatString);
                                     writer.write(System.lineSeparator());
                                     writer.write(firstLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(secondLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(thirdLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fourthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fifthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(sixthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write("REPEND");
                                     writer.write(System.lineSeparator());
                                     writer.flush();
                                     writer.close();
                                 }

                                 if (lastLine.length() == 8) {
                                    is8Color = true;
    //     System.out.println(repeats);
                                     char characterA = lastLine.charAt(0);
                                     char characterB = lastLine.charAt(1);
                                     char characterC = lastLine.charAt(2);
                                     char characterD = lastLine.charAt(3);
                                     char characterE = lastLine.charAt(4);
                                     char characterF = lastLine.charAt(5);
                                     char characterG = lastLine.charAt(6);
                                     char characterH = lastLine.charAt(7);
                                     char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                     char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                     char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                     char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                     char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                     char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                     char carrierG = carriers.get(colors.indexOf(String.valueOf(characterG))).charAt(0);
                                     char carrierH = carriers.get(colors.indexOf(String.valueOf(characterH))).charAt(0);

                                     if(String.valueOf(fullLine.charAt(0)).equals("X")){
                                         isX=true;
                                         repeats+=2;
                                     }

                                     // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                     StringBuilder firstLine = new StringBuilder(eightColorA);
                                     String secondLine = eightColorB;
                                     StringBuilder thirdLine = new StringBuilder(eightColorC);
                                     StringBuilder fourthLine = new StringBuilder(eightColorD);
                                     String fifthLine = eightColorE;
                                     StringBuilder sixthLine = new StringBuilder(eightColorF);

                                     firstLine.setCharAt(14,characterA);
                                     firstLine.setCharAt(25,characterB);
                                     firstLine.setCharAt(29,characterC);
                                     firstLine.setCharAt(33,characterD);

                                     firstLine.setCharAt(40,carrierA);
                                     firstLine.setCharAt(42,carrierB);
                                     firstLine.setCharAt(44,carrierC);
                                     firstLine.setCharAt(46,carrierD);
                                     firstLine.setCharAt(48,carrierE);
                                     firstLine.setCharAt(50,carrierF);
                                     firstLine.setCharAt(52,carrierG);
                                     firstLine.setCharAt(54,carrierH);

                                     thirdLine.setCharAt(10,characterE);
                                     thirdLine.setCharAt(14,characterF);
                                     thirdLine.setCharAt(18,characterG);
                                     thirdLine.setCharAt(22,characterH);


                                     fourthLine.setCharAt(14,characterA);
                                     fourthLine.setCharAt(25,characterB);
                                     fourthLine.setCharAt(29,characterC);
                                     fourthLine.setCharAt(33,characterD);

                                     sixthLine.setCharAt(10,characterE);
                                     sixthLine.setCharAt(14,characterF);
                                     sixthLine.setCharAt(18,characterG);
                                     sixthLine.setCharAt(22,characterH);


                                     String repeatString = "REP*"+String.valueOf(repeats/2);
                                     writer = new FileWriter(sintralNew,true);
                                     writer.write(repeatString);
                                     writer.write(System.lineSeparator());
                                     writer.write(firstLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(secondLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(thirdLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fourthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(fifthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write(sixthLine.toString());
                                     writer.write(System.lineSeparator());
                                     writer.write("REPEND");
                                     writer.write(System.lineSeparator());
                                     writer.flush();
                                     writer.close();
                                 }

                                repeats=1;

                                 if(String.valueOf(fullLine.charAt(0)).equals("z")){


                                    String[] persSize = {"16","24","40"};
                                    String persSizeString = (String)JOptionPane.showInputDialog(null,"Choose a size","Choose a size",JOptionPane.QUESTION_MESSAGE,null,persSize,persSize[1]);

                                    if(persSizeString.equals("16")){
                                        StringBuilder pers2 = new StringBuilder(JA2Reps);
                                        pers2.setCharAt(9,'0');
                                        pers2.setCharAt(10,'8');

                                        if(String.valueOf(fullLine.charAt(2)).equals("S")){
                                            pers2.setCharAt(9,'0');
                                            pers2.setCharAt(10,'4');
                                        }
                                       // pers2.setCharAt(10,'2');
                                       // writer.write(pers1.toString());
                                        for(int templateLine = 70;templateLine<97;templateLine++){
                                            writer = new FileWriter(sintralNew,true);
                                            String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(templateLine);
                                            writer.write(currentTemplateLine);
                                            writer.write(System.getProperty( "line.separator" ));
                                            writer.flush();
                                            writer.close();
                                        }

                                        for(int skips=0;skips<16;skips++){
                                            bufferedReader.readLine();
                                            if(String.valueOf(line.charAt(5)).equals("$")){
                                                skips--;
                                            }
                                        }

                                        writer = new FileWriter(sintralNew,true);
                                        writer.write(pers2.toString());
                                        writer.write(System.getProperty( "line.separator" ));
                                        writer.flush();
                                        writer.close();
                                    }

                                   else if(persSizeString.equals("24")){
                                        StringBuilder pers2 = new StringBuilder(JA2Reps);
                                        pers2.setCharAt(9,'1');
                                        pers2.setCharAt(10,'2');

                                        if(String.valueOf(fullLine.charAt(2)).equals("S")){
                                            pers2.setCharAt(9,'0');
                                            pers2.setCharAt(10,'6');
                                        }
                                     //   writer.write(pers1.toString());
                                        for(int templateLine = 97;templateLine<124;templateLine++){
                                            writer = new FileWriter(sintralNew,true);
                                            String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(templateLine);
                                            writer.write(currentTemplateLine);
                                            writer.write(System.getProperty( "line.separator" ));
                                            writer.flush();
                                            writer.close();
                                        }

                                        for(int skips=0;skips<24;skips++){
                                            bufferedReader.readLine();
                                            if(String.valueOf(line.charAt(5)).equals("$")){
                                                skips--;
                                            }
                                        }

                                        writer = new FileWriter(sintralNew,true);
                                        writer.write(pers2.toString());
                                        writer.write(System.getProperty( "line.separator" ));
                                        writer.flush();
                                        writer.close();
                                    }

                                   else if(persSizeString.equals("40")){
                                        StringBuilder pers2 = new StringBuilder(JA2Reps);

                                        pers2.setCharAt(9,'2');
                                        pers2.setCharAt(10,'0');
                                        if(String.valueOf(fullLine.charAt(2)).equals("S")){
                                            pers2.setCharAt(9,'1');
                                            pers2.setCharAt(10,'0');
                                        }
                                        for(int templateLine = 124;templateLine<151;templateLine++){
                                            writer = new FileWriter(sintralNew,true);
                                            String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(templateLine);
                                            writer.write(currentTemplateLine);
                                            writer.write(System.getProperty( "line.separator" ));
                                            writer.flush();
                                            writer.close();
                                        }

                                        for(int skips=0;skips<40;skips++){
                                            line=bufferedReader.readLine();
                                            System.out.println(line);
                                            if(String.valueOf(line.charAt(5)).equals("$")){
                                                skips--;
                                            }
                                        }

                                        writer = new FileWriter(sintralNew,true);
                                        writer.write(pers2.toString());
                                        writer.write(System.getProperty( "line.separator" ));
                                        writer.flush();
                                        writer.close();
                                    }

                                    if(fullLine.length() == 3){
                                     //   writer.write(JA2Reps);
                                      //  writer.flush();
                                      //  writer.close();

                                        if(String.valueOf(fullLine.charAt(2)).equals("S")){
                                           //System.out.println(tcPersA);
                                            writer = new FileWriter(sintralNew,true);
                                            writer.write(System.lineSeparator());
                                            writer.write(tcPersA);
                                            writer.write(System.lineSeparator());
                                            writer.write(tcPersB);
                                            writer.write(System.lineSeparator());
                                            writer.write("JA1=??");
                                            writer.write(System.lineSeparator());
                                            writer.flush();
                                            writer.close();
                                        }

                                        else {
                                            // String repeatString = "REP*"+String.valueOf(repeats/2)+"\n";
                                            writer = new FileWriter(sintralNew, true);
                                            writer.write(System.lineSeparator());
                                            writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(152));
                                            writer.write(System.lineSeparator());
                                            writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(153));
                                            writer.write(System.lineSeparator());
                                            writer.write(tcPersA);
                                            writer.write(System.lineSeparator());
                                            writer.write(tcPersB);
                                            writer.write(System.lineSeparator());
                                            writer.write("REPEND");
                                            writer.write(System.lineSeparator());
                                            writer.write("JA1=??");
                                            writer.write(System.lineSeparator());
                                            writer.flush();
                                            writer.close();
                                        }
                                    }

                                    else if(fullLine.length() == 4){
                                       // writer.write(pers2.toString());
                                        //  writer.flush();
                                        //  writer.close();

                                       // String repeatString = "REP*"+String.valueOf(repeats/2)+"\n";
                                        writer = new FileWriter(sintralNew,true);
                                        writer.write(System.lineSeparator());
                                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(156));
                                        writer.write(System.lineSeparator());
                                        writer.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(157));
                                        writer.write(System.lineSeparator());
                                        writer.write("REPEND");
                                        writer.write(System.lineSeparator());
                                        writer.write("JA1=??");
                                        writer.write(System.lineSeparator());
                                        writer.flush();
                                        writer.close();

                                    }





                                }

                            }

                            else{
                                repeats++;
                            }

                        }

                        writer = new FileWriter(sintralNew,true);





                        for(int templateLine = 163;templateLine<227;templateLine++){
                            if(templateLine == 188 && is8Color){
                                writer.write(" 919    YG:8/12345678;");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 189 && is8Color){
                                writer.write(" 920 \tYD8=7-4");
                                writer.write(System.getProperty("line.separator"));
                                }
                            else if(templateLine == 189 && is7Color){
                                writer.write(" 920 \tYD8=53-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 189 && is6Color){
                                writer.write(" 920 \tYD8=46-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 189 && is5Color){
                                writer.write(" 920 \tYD8=39-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 189 && is4Color){
                                writer.write(" 920 \tYD8=32-4");
                                writer.write(System.getProperty("line.separator"));
                            }

                            else if(templateLine == 218 && is8Color){
                                writer.write(" 949    YG:8/12345678;");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 218 && isDoubleProd){
                                writer.write("949    YG:8/12233445678;");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 219 && is8Color){
                                writer.write(" 950 \tYD8=7-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 219 && is7Color){
                                writer.write(" 950 \tYD8=53-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 219 && is6Color){
                                writer.write(" 950 \tYD8=46-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 219 && is5Color){
                                writer.write(" 950 \tYD8=39-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else if(templateLine == 219 && is4Color){
                                writer.write(" 950 \tYD8=32-4");
                                writer.write(System.getProperty("line.separator"));
                            }
                            else {
                                String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/template1.txt")).get(templateLine);
                                writer.write(currentTemplateLine);
                                writer.write(System.getProperty("line.separator"));
                            }
                        }

                        writer.flush();
                        writer.close();

                    }
                    catch(FileNotFoundException e1){
                        e1.printStackTrace();
                    } catch(IOException e1){
                        e1.printStackTrace();
                    }
                }
                if(isX==false){
                    JOptionPane.showMessageDialog(null,"I don't think X marks the spot...");
                    return;
                }
            }
        });



        birdseyeSintralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isX = false;
                //    int numLines[] = {0, 0, 1, 0, 0, 0, 0, 0};

                if (e.getSource() == birdseyeSintralButton) {
                    int returnVal = fc.showOpenDialog(Barcode.this);
                    File file = null;
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                    }
                    String inputName = JOptionPane.showInputDialog(null, "Name your file.");
                    String filenameTC = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/" + inputName + "TC.txt";
                    String filenameTC2X = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/" + inputName + "TC2X.txt";
                    String filename440 = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/" + inputName + "440.txt";
                    File sintralNewTC = new File(filenameTC);
                    File sintralNewTC2X = new File(filenameTC2X);
                    File sintralNew440 = new File(filename440);
                    FileReader fileReader = null;
                    FileReader templateReader = null;
                    ArrayList<String> colors = new ArrayList<>(8);
                    colors.add(".");
                    colors.add("A");
                    colors.add("Y");
                    colors.add("T");
                    colors.add("*");
                    colors.add("I");
                    colors.add("+");
                    colors.add("B");
                    ArrayList<String> offsets = new ArrayList<>(8);
                    offsets.add("G");
                    offsets.add("H");
                    offsets.add("O");
                    offsets.add("W");
                    offsets.add("Z");
                    offsets.add("E");
                    offsets.add("K");
                    offsets.add("L");
                    ArrayList<String> carriers = new ArrayList<>(8);
                    carriers.add("2");
                    carriers.add("3");
                    carriers.add("4");
                    carriers.add("5");
                    carriers.add("6");
                    carriers.add("7");
                    carriers.add("1");
                    carriers.add("8");
                    try {

                        String threeColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(21);
                        String threeColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(22);
                        String threeColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(24);
                        String threeColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(25);
                        String fourColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(28);
                        String fourColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(29);
                        String fiveColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(32);
                        String fiveColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(33);
                        String fiveColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(34);
                        String fiveColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(35);
                        String fiveColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(36);
                        String fiveColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(37);
                        String fiveColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(40);
                        String fiveColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(41);
                        String sixColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(44);
                        String sixColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(45);
                        String sixColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(46);
                        String sixColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(47);
                        String sixColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(48);
                        String sixColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(49);
                        String sixColorAtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(52);
                        String sixColorBtc = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(53);
                        String sevenColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(56);
                        String sevenColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(57);
                        String sevenColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(58);
                        String sevenColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(59);
                        String sevenColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(60);
                        String sevenColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(61);
                        String eightColorA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(64);
                        String eightColorB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(65);
                        String eightColorC = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(66);
                        String eightColorD = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(67);
                        String eightColorE = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(68);
                        String eightColorF = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(69);
                        //  System.out.println(eightColorA);
                        String tcPersA = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(160);
                        String tcPersB = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(161);

                        String JA2Reps = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(151);

                        fileReader = new FileReader(file);
                        //     templateReader = new FileReader("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        //  int colorsOnLines[] = {0, 0, 0, 0, 0, 0, 0, 0};
                        int numColors = 0;
                        int lastNumColors;
                        String current;
                        int repeats = 0;
                        String lastLine;
                        String fullLine = "";
                        ArrayList<String> list = new ArrayList<String>();
                        int lineNumber = -1;

                        FileWriter writerTC = new FileWriter(sintralNewTC, true);
                        FileWriter writerTC2X = new FileWriter(sintralNewTC2X, true);
                        FileWriter writer440 = new FileWriter(sintralNew440, true);

                        for (int templateLine = 0; templateLine < 19; templateLine++) {
                            String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(templateLine);
                            writerTC.write(currentTemplateLine);
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(currentTemplateLine);
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(currentTemplateLine);
                            writer440.write(System.getProperty("line.separator"));
                        }

                        writerTC.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(164));
                        writerTC.write(System.getProperty("line.separator"));
                        writerTC.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(165));
                        writerTC.write(System.getProperty("line.separator"));
                        writerTC.flush();
                        writerTC.close();
                        writerTC2X.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(164));
                        writerTC2X.write(System.getProperty("line.separator"));
                        writerTC2X.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(165));
                        writerTC2X.write(System.getProperty("line.separator"));
                        writerTC2X.flush();
                        writerTC2X.close();
                        writer440.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(164));
                        writer440.write(System.getProperty("line.separator"));
                        writer440.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(165));
                        writer440.write(System.getProperty("line.separator"));
                        writer440.flush();
                        writer440.close();

                        while ((line = bufferedReader.readLine()) != null) {
                            lineNumber++;
                            lastLine = fullLine;
                            fullLine = "";
                            lastNumColors = numColors;
                            numColors = 2;
                            // System.out.println(line);

                            if (String.valueOf(line.charAt(5)).equals("$")) {
                                fullLine = lastLine;
                                repeats--;
                            } else if (String.valueOf(line.charAt(5)).equals(".") || String.valueOf(line.charAt(5)).equals("X") || String.valueOf(line.charAt(5)).equals("z") || String.valueOf(line.charAt(5)).equals("A") || String.valueOf(line.charAt(5)).equals("Y") || String.valueOf(line.charAt(5)).equals("T")) {
                                fullLine += String.valueOf(line.charAt(5));
                                fullLine += String.valueOf(line.charAt(6));
                                fullLine += String.valueOf(line.charAt(7));
                                numColors++;
                                current = String.valueOf(line.charAt(8));
                                if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                    numColors++;
                                    fullLine += current;
                                    current = String.valueOf(line.charAt(9));

                                    if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                        numColors++;
                                        fullLine += current;
                                        current = String.valueOf(line.charAt(10));

                                        if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                            numColors++;
                                            fullLine += current;
                                            current = String.valueOf(line.charAt(11));

                                            if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                                numColors++;
                                                fullLine += current;
                                                current = String.valueOf(line.charAt(12));

                                                if (current.equals("A") || current.equals("Y") || current.equals("T") || current.equals("*") || current.equals("I") || current.equals("+") || current.equals("B")) {
                                                    numColors++;
                                                    fullLine += current;
                                                }
                                            }
                                        }
                                    }
                                }
                            }


                            if (lineNumber > 0 && lastLine.equals(fullLine) == false && repeats > 1) {

                                if (repeats % 2 != 0 && !String.valueOf(fullLine.charAt(0)).equals("X")) {
                                    JOptionPane.showMessageDialog(null, "Oh shit...I think the barcode is wrong.");
                                    return;
                                }

                                if (lastLine.length() == 3 && repeats > 1) {

                                    char characterA = lastLine.charAt(0);
                                    char carrierA;
                                    char offsetA;

                                    if (String.valueOf(characterA).equals("z")) {
                                        carrierA = carriers.get(colors.indexOf(".")).charAt(0);
                                        offsetA = offsets.get(colors.indexOf(".")).charAt(0);
                                    } else {
                                        carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                        offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    }

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);

                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);

                                    char carrierC;
                                    char offsetC;

                                    if (String.valueOf(characterC).equals("S")) {
                                        carrierC = carriers.get(colors.indexOf("Y")).charAt(0);
                                        offsetC = carriers.get(colors.indexOf("Y")).charAt(0);
                                    } else {
                                        carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                        offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    }

                                        StringBuilder firstLine = new StringBuilder(threeColorA);
                                        StringBuilder secondLine = new StringBuilder(threeColorB);

                                        firstLine.setCharAt(14, characterA);
                                        firstLine.setCharAt(15, offsetA);

                                        firstLine.setCharAt(25, characterB);
                                        firstLine.setCharAt(26, offsetB);
                                        firstLine.setCharAt(28, characterB);
                                        firstLine.setCharAt(29, offsetB);
                                        firstLine.setCharAt(30, characterA);
                                        firstLine.setCharAt(31, offsetA);
                                        firstLine.setCharAt(32, offsetC);

                                        firstLine.setCharAt(34, characterC);
                                        firstLine.setCharAt(35, offsetC);
                                        firstLine.setCharAt(37, characterC);
                                        firstLine.setCharAt(38, offsetC);
                                        firstLine.setCharAt(39, characterA);
                                        firstLine.setCharAt(40, characterB);

                                        firstLine.setCharAt(46, carrierA);
                                        firstLine.setCharAt(48, carrierB);
                                        firstLine.setCharAt(50, carrierC);


                                        secondLine.setCharAt(14, characterA);
                                        secondLine.setCharAt(15, offsetA);

                                        secondLine.setCharAt(25, characterB);
                                        secondLine.setCharAt(26, offsetB);
                                        secondLine.setCharAt(28, characterB);
                                        secondLine.setCharAt(29, offsetB);
                                        secondLine.setCharAt(30, characterA);
                                        secondLine.setCharAt(31, offsetA);
                                        secondLine.setCharAt(32, offsetC);

                                        secondLine.setCharAt(34, characterC);
                                        secondLine.setCharAt(35, offsetC);
                                        secondLine.setCharAt(37, characterC);
                                        secondLine.setCharAt(38, offsetC);
                                        secondLine.setCharAt(39, characterA);
                                        secondLine.setCharAt(40, characterB);

                                        secondLine.setCharAt(46, carrierA);
                                        secondLine.setCharAt(48, carrierB);
                                        secondLine.setCharAt(50, carrierC);
                                        //  System.out.println("fullline="+fullLine);
                                        //  System.out.println("lastline="+lastLine);

                                        String repeatString = "REP*" + String.valueOf(repeats / 2) + "\n";
                                        String repeatString2X = "REP*" + String.valueOf(repeats / 4) + "\n";
                                        writerTC = new FileWriter(sintralNewTC, true);
                                        writer440 = new FileWriter(sintralNew440, true);

                                        writerTC.write(repeatString);
                                        writerTC.write(System.lineSeparator());
                                        writerTC.write(firstLine.toString());
                                        writerTC.write(System.lineSeparator());
                                        writerTC.write(secondLine.toString());
                                        writerTC.write(System.lineSeparator());
                                        writerTC.write("REPEND");
                                        writerTC.write(System.lineSeparator());
                                        writerTC.flush();
                                        writerTC.close();

                                        if ((repeats / 2) % 2 != 0 && lastLine.equals(".AY")) {
                                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                                            repeats -= 2;
                                            repeats=repeats/4;
                                            repeatString2X = "REP*" + String.valueOf(repeats) + "\n";
                                            writerTC2X.write(repeatString2X);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(threeColorAtc);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(threeColorBtc);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write("REPEND");
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(firstLine.toString());
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(secondLine.toString());
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.flush();
                                            writerTC2X.close();
                                        } else if (lastLine.equals(".AY")) {
                                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                                            writerTC2X.write(repeatString2X);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(threeColorAtc);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(threeColorBtc);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write("REPEND");
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.flush();
                                            writerTC2X.close();
                                        }
                                        else {
                                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                                            writerTC2X.write(repeatString);
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(firstLine.toString());
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write(secondLine.toString());
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.write("REPEND");
                                            writerTC2X.write(System.lineSeparator());
                                            writerTC2X.flush();
                                            writerTC2X.close();
                                        }

                                        writer440.write(repeatString);
                                        writer440.write(System.lineSeparator());
                                        writer440.write(firstLine.toString());
                                        writer440.write(System.lineSeparator());
                                        writer440.write(secondLine.toString());
                                        writer440.write(System.lineSeparator());
                                        writer440.write("REPEND");
                                        writer440.write(System.lineSeparator());
                                        writer440.flush();
                                        writer440.close();
                                }


                                if (lastLine.length() == 4) {
                                    //    System.out.println(repeats);
                                    is4Color = true;
                                    char characterA = lastLine.charAt(0);
                                    char carrierA;
                                    char offsetA;

                                    if (String.valueOf(characterA).equals("z")) {
                                        carrierA = carriers.get(colors.indexOf(".")).charAt(0);
                                        offsetA = offsets.get(colors.indexOf(".")).charAt(0);
                                    } else {
                                        carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                        offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    }

                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);
                                    char characterD = lastLine.charAt(3);
                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char offsetD = offsets.get(colors.indexOf(String.valueOf(characterD))).charAt(0);

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                    StringBuilder firstLine = new StringBuilder(fourColorA);
                                    StringBuilder secondLine = new StringBuilder(fourColorB);

                                    firstLine.setCharAt(14, characterA);
                                    firstLine.setCharAt(15, offsetA);
                                    firstLine.setCharAt(20, offsetA);
                                    firstLine.setCharAt(33, characterB);
                                    firstLine.setCharAt(34, offsetB);
                                    firstLine.setCharAt(36, characterB);
                                    firstLine.setCharAt(46, characterC);
                                    firstLine.setCharAt(47, offsetC);
                                    firstLine.setCharAt(49, offsetC);
                                    firstLine.setCharAt(59, characterD);
                                    firstLine.setCharAt(60, offsetD);
                                    firstLine.setCharAt(62, characterD);
                                    firstLine.setCharAt(75, carrierA);
                                    firstLine.setCharAt(77, carrierB);
                                    firstLine.setCharAt(79, carrierC);
                                    firstLine.setCharAt(81, carrierD);
                                    secondLine.setCharAt(14, characterA);
                                    secondLine.setCharAt(15, offsetA);
                                    secondLine.setCharAt(20, offsetA);
                                    secondLine.setCharAt(33, characterB);
                                    secondLine.setCharAt(34, offsetB);
                                    secondLine.setCharAt(36, characterB);
                                    secondLine.setCharAt(46, characterC);
                                    secondLine.setCharAt(47, offsetC);
                                    secondLine.setCharAt(49, offsetC);
                                    secondLine.setCharAt(59, characterD);
                                    secondLine.setCharAt(60, offsetD);
                                    secondLine.setCharAt(62, characterD);
                                    secondLine.setCharAt(75, carrierA);
                                    secondLine.setCharAt(77, carrierB);
                                    secondLine.setCharAt(79, carrierC);
                                    secondLine.setCharAt(81, carrierD);
                                    // secondLine.append((char)10);
                                    // System.out.println("fullline="+fullLine);
                                    // System.out.println("lastline="+lastLine);

                                    String repeatString = "REP*" + String.valueOf(repeats / 2);
                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);
                                    writerTC.write(repeatString);
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(firstLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(secondLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();
                                    writerTC2X.write(repeatString);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(firstLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(secondLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();
                                    writer440.write(repeatString);
                                    writer440.write(System.lineSeparator());
                                    writer440.write(firstLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(secondLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();

                                }

                                if (lastLine.length() == 5) {
                                    is5Color = true;
                                    //   System.out.println(repeats);
                                    char characterA = lastLine.charAt(0);
                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);
                                    char characterD = lastLine.charAt(3);
                                    char characterE = lastLine.charAt(4);
                                    char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char offsetD = offsets.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char offsetE = offsets.get(colors.indexOf(String.valueOf(characterE))).charAt(0);

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                    StringBuilder firstLine = new StringBuilder(fiveColorA);
                                    String secondLine = fiveColorB;
                                    StringBuilder thirdLine = new StringBuilder(fiveColorC);
                                    StringBuilder fourthLine = new StringBuilder(fiveColorD);
                                    String fifthLine = fiveColorE;
                                    StringBuilder sixthLine = new StringBuilder(fiveColorF);

                                    StringBuilder tcOne = new StringBuilder(fiveColorAtc);
                                    StringBuilder tcTwo = new StringBuilder(fiveColorBtc);

                                    firstLine.setCharAt(14, characterA);
                                    firstLine.setCharAt(15, offsetA);
                                    firstLine.setCharAt(20, offsetA);
                                    firstLine.setCharAt(33, characterB);
                                    firstLine.setCharAt(34, offsetB);
                                    firstLine.setCharAt(36, characterB);
                                    firstLine.setCharAt(46, characterC);
                                    firstLine.setCharAt(47, offsetC);
                                    firstLine.setCharAt(49, offsetC);
                                    firstLine.setCharAt(59, characterD);
                                    firstLine.setCharAt(60, offsetD);
                                    firstLine.setCharAt(62, characterD);
                                    firstLine.setCharAt(75, carrierA);
                                    firstLine.setCharAt(77, carrierB);
                                    firstLine.setCharAt(79, carrierC);
                                    firstLine.setCharAt(81, carrierD);
                                    firstLine.setCharAt(83, carrierE);

                                    tcOne.setCharAt(15, characterA);
                                    tcOne.setCharAt(16, offsetA);
                                    tcOne.setCharAt(21, offsetA);
                                    tcOne.setCharAt(34, characterB);
                                    tcOne.setCharAt(35, offsetB);
                                    tcOne.setCharAt(37, characterB);
                                    tcOne.setCharAt(47, characterC);
                                    tcOne.setCharAt(48, offsetC);
                                    tcOne.setCharAt(50, offsetC);
                                    tcOne.setCharAt(60, characterD);
                                    tcOne.setCharAt(61, offsetD);
                                    tcOne.setCharAt(63, characterD);
                                    tcOne.setCharAt(73, characterE);
                                    tcOne.setCharAt(74, offsetE);
                                    tcOne.setCharAt(76, offsetE);
                                    tcOne.setCharAt(89, carrierA);
                                    tcOne.setCharAt(91, carrierB);
                                    tcOne.setCharAt(93, carrierC);
                                    tcOne.setCharAt(95, carrierD);
                                    tcOne.setCharAt(97, carrierE);

                                    tcTwo.setCharAt(15, characterA);
                                    tcTwo.setCharAt(16, offsetA);
                                    tcTwo.setCharAt(21, offsetA);
                                    tcTwo.setCharAt(34, characterB);
                                    tcTwo.setCharAt(35, offsetB);
                                    tcTwo.setCharAt(37, characterB);
                                    tcTwo.setCharAt(47, characterC);
                                    tcTwo.setCharAt(48, offsetC);
                                    tcTwo.setCharAt(50, offsetC);
                                    tcTwo.setCharAt(60, characterD);
                                    tcTwo.setCharAt(61, offsetD);
                                    tcTwo.setCharAt(63, characterD);
                                    tcTwo.setCharAt(73, characterE);
                                    tcTwo.setCharAt(74, offsetE);
                                    tcTwo.setCharAt(76, offsetE);
                                    tcTwo.setCharAt(89, carrierA);
                                    tcTwo.setCharAt(91, carrierB);
                                    tcTwo.setCharAt(93, carrierC);
                                    tcTwo.setCharAt(95, carrierD);
                                    tcTwo.setCharAt(97, carrierE);

                                    thirdLine.setCharAt(10, characterE);
                                    thirdLine.setCharAt(11, offsetE);
                                    thirdLine.setCharAt(13, offsetE);


                                    fourthLine.setCharAt(14, characterA);
                                    fourthLine.setCharAt(15, offsetA);
                                    fourthLine.setCharAt(20, offsetA);
                                    fourthLine.setCharAt(33, characterB);
                                    fourthLine.setCharAt(34, offsetB);
                                    fourthLine.setCharAt(36, characterB);
                                    fourthLine.setCharAt(46, characterC);
                                    fourthLine.setCharAt(47, offsetC);
                                    fourthLine.setCharAt(49, offsetC);
                                    fourthLine.setCharAt(59, characterD);
                                    fourthLine.setCharAt(60, offsetD);
                                    fourthLine.setCharAt(62, characterD);

                                    sixthLine.setCharAt(10, characterE);
                                    sixthLine.setCharAt(11, offsetE);
                                    sixthLine.setCharAt(13, offsetE);


                                    String repeatString = "REP*" + String.valueOf(repeats / 2);
                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);

                                    writer440.write(repeatString);
                                    writer440.write(System.lineSeparator());
                                    writer440.write(firstLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(secondLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(thirdLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fourthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fifthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(sixthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();

                                    writerTC.write(repeatString);
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(tcOne.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(tcTwo.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();

                                    writerTC2X.write(repeatString);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(tcOne.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(tcTwo.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();
                                }

                                if (lastLine.length() == 6) {
                                    is6Color = true;
                                    //System.out.println(repeats);
                                    char characterA = lastLine.charAt(0);
                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);
                                    char characterD = lastLine.charAt(3);
                                    char characterE = lastLine.charAt(4);
                                    char characterF = lastLine.charAt(5);
                                    char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                    char offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char offsetD = offsets.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char offsetE = offsets.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char offsetF = offsets.get(colors.indexOf(String.valueOf(characterF))).charAt(0);

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                    StringBuilder firstLine = new StringBuilder(sixColorA);
                                    String secondLine = sixColorB;
                                    StringBuilder thirdLine = new StringBuilder(sixColorC);
                                    StringBuilder fourthLine = new StringBuilder(sixColorD);
                                    String fifthLine = sixColorE;
                                    StringBuilder sixthLine = new StringBuilder(sixColorF);
                                    StringBuilder tcOne = new StringBuilder(sixColorAtc);
                                    StringBuilder tcTwo = new StringBuilder(sixColorBtc);


                                    firstLine.setCharAt(14, characterA);
                                    firstLine.setCharAt(15, offsetA);
                                    firstLine.setCharAt(20, offsetA);
                                    firstLine.setCharAt(33, characterB);
                                    firstLine.setCharAt(34, offsetB);
                                    firstLine.setCharAt(36, characterB);
                                    firstLine.setCharAt(46, characterC);
                                    firstLine.setCharAt(47, offsetC);
                                    firstLine.setCharAt(49, offsetC);
                                    firstLine.setCharAt(59, characterD);
                                    firstLine.setCharAt(60, offsetD);
                                    firstLine.setCharAt(62, characterD);
                                    firstLine.setCharAt(75, carrierA);
                                    firstLine.setCharAt(77, carrierB);
                                    firstLine.setCharAt(79, carrierC);
                                    firstLine.setCharAt(81, carrierD);
                                    firstLine.setCharAt(83, carrierE);
                                    firstLine.setCharAt(85, carrierF);

                                    thirdLine.setCharAt(10, characterE);
                                    thirdLine.setCharAt(11, offsetE);
                                    thirdLine.setCharAt(13, offsetE);
                                    thirdLine.setCharAt(23, characterF);
                                    thirdLine.setCharAt(24, offsetF);
                                    thirdLine.setCharAt(26, characterF);


                                    fourthLine.setCharAt(14, characterA);
                                    fourthLine.setCharAt(15, offsetA);
                                    fourthLine.setCharAt(20, offsetA);
                                    fourthLine.setCharAt(33, characterB);
                                    fourthLine.setCharAt(34, offsetB);
                                    fourthLine.setCharAt(36, characterB);
                                    fourthLine.setCharAt(46, characterC);
                                    fourthLine.setCharAt(47, offsetC);
                                    fourthLine.setCharAt(49, offsetC);
                                    fourthLine.setCharAt(59, characterD);
                                    fourthLine.setCharAt(60, offsetD);
                                    fourthLine.setCharAt(62, characterD);

                                    sixthLine.setCharAt(10, characterE);
                                    sixthLine.setCharAt(11, offsetE);
                                    sixthLine.setCharAt(13, offsetE);
                                    sixthLine.setCharAt(23, characterF);
                                    sixthLine.setCharAt(24, offsetF);
                                    sixthLine.setCharAt(26, characterF);


                                    tcOne.setCharAt(15, characterA);
                                    tcOne.setCharAt(16, offsetA);
                                    tcOne.setCharAt(21, offsetA);
                                    tcOne.setCharAt(34, characterB);
                                    tcOne.setCharAt(35, offsetB);
                                    tcOne.setCharAt(37, characterB);
                                    tcOne.setCharAt(47, characterC);
                                    tcOne.setCharAt(48, offsetC);
                                    tcOne.setCharAt(50, offsetC);
                                    tcOne.setCharAt(60, characterD);
                                    tcOne.setCharAt(61, offsetD);
                                    tcOne.setCharAt(63, characterD);
                                    tcOne.setCharAt(73, characterE);
                                    tcOne.setCharAt(74, offsetE);
                                    tcOne.setCharAt(76, offsetE);
                                    tcOne.setCharAt(86, characterF);
                                    tcOne.setCharAt(87, offsetF);
                                    tcOne.setCharAt(89, characterF);
                                    tcOne.setCharAt(102, carrierA);
                                    tcOne.setCharAt(104, carrierB);
                                    tcOne.setCharAt(106, carrierC);
                                    tcOne.setCharAt(108, carrierD);
                                    tcOne.setCharAt(110, carrierE);
                                    tcOne.setCharAt(112, carrierF);

                                    tcTwo.setCharAt(15, characterA);
                                    tcTwo.setCharAt(16, offsetA);
                                    tcTwo.setCharAt(21, offsetA);
                                    tcTwo.setCharAt(34, characterB);
                                    tcTwo.setCharAt(35, offsetB);
                                    tcTwo.setCharAt(37, characterB);
                                    tcTwo.setCharAt(47, characterC);
                                    tcTwo.setCharAt(48, offsetC);
                                    tcTwo.setCharAt(50, offsetC);
                                    tcTwo.setCharAt(60, characterD);
                                    tcTwo.setCharAt(61, offsetD);
                                    tcTwo.setCharAt(63, characterD);
                                    tcTwo.setCharAt(73, characterE);
                                    tcTwo.setCharAt(74, offsetE);
                                    tcTwo.setCharAt(76, offsetE);
                                    tcTwo.setCharAt(86, characterF);
                                    tcTwo.setCharAt(87, offsetF);
                                    tcTwo.setCharAt(89, characterF);
                                    tcTwo.setCharAt(102, carrierA);
                                    tcTwo.setCharAt(104, carrierB);
                                    tcTwo.setCharAt(106, carrierC);
                                    tcTwo.setCharAt(108, carrierD);
                                    tcTwo.setCharAt(110, carrierE);
                                    tcTwo.setCharAt(112, carrierF);



                                    String repeatString = "REP*" + String.valueOf(repeats / 2);
                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);

                                    writer440.write(repeatString);
                                    writer440.write(System.lineSeparator());
                                    writer440.write(firstLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(secondLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(thirdLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fourthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fifthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(sixthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();

                                    writerTC.write(repeatString);
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(tcOne.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(tcTwo.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();

                                    writerTC2X.write(repeatString);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(tcOne.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(tcTwo.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();
                                }

                                if (lastLine.length() == 7) {
                                    is7Color = true;
                                    // System.out.println(repeats);
                                    char characterA = lastLine.charAt(0);
                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);
                                    char characterD = lastLine.charAt(3);
                                    char characterE = lastLine.charAt(4);
                                    char characterF = lastLine.charAt(5);
                                    char characterG = lastLine.charAt(6);
                                    char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                    char carrierG = carriers.get(colors.indexOf(String.valueOf(characterG))).charAt(0);
                                    char offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char offsetD = offsets.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char offsetE = offsets.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char offsetF = offsets.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                    char offsetG = offsets.get(colors.indexOf(String.valueOf(characterG))).charAt(0);

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                    StringBuilder firstLine = new StringBuilder(sevenColorA);
                                    String secondLine = sevenColorB;
                                    StringBuilder thirdLine = new StringBuilder(sevenColorC);
                                    StringBuilder fourthLine = new StringBuilder(sevenColorD);
                                    String fifthLine = sevenColorE;
                                    StringBuilder sixthLine = new StringBuilder(sevenColorF);

                                    firstLine.setCharAt(14, characterA);
                                    firstLine.setCharAt(15, offsetA);
                                    firstLine.setCharAt(20, offsetA);
                                    firstLine.setCharAt(34, characterB);
                                    firstLine.setCharAt(35, offsetB);
                                    firstLine.setCharAt(37, characterB);
                                    firstLine.setCharAt(47, characterC);
                                    firstLine.setCharAt(48, offsetC);
                                    firstLine.setCharAt(50, offsetC);
                                    firstLine.setCharAt(60, characterD);
                                    firstLine.setCharAt(61, offsetD);
                                    firstLine.setCharAt(63, characterD);
                                    firstLine.setCharAt(76, carrierA);
                                    firstLine.setCharAt(78, carrierB);
                                    firstLine.setCharAt(80, carrierC);
                                    firstLine.setCharAt(82, carrierD);
                                    firstLine.setCharAt(84, carrierE);
                                    firstLine.setCharAt(86, carrierF);
                                    firstLine.setCharAt(88, carrierG);

                                    thirdLine.setCharAt(10, characterE);
                                    thirdLine.setCharAt(11, offsetE);
                                    thirdLine.setCharAt(13, offsetE);
                                    thirdLine.setCharAt(23, characterF);
                                    thirdLine.setCharAt(24, offsetF);
                                    thirdLine.setCharAt(26, characterF);
                                    thirdLine.setCharAt(36, characterG);
                                    thirdLine.setCharAt(37, offsetG);
                                    thirdLine.setCharAt(39, offsetG);


                                    fourthLine.setCharAt(14, characterA);
                                    fourthLine.setCharAt(15, offsetA);
                                    fourthLine.setCharAt(20, offsetA);
                                    fourthLine.setCharAt(34, characterB);
                                    fourthLine.setCharAt(35, offsetB);
                                    fourthLine.setCharAt(37, characterB);
                                    fourthLine.setCharAt(47, characterC);
                                    fourthLine.setCharAt(48, offsetC);
                                    fourthLine.setCharAt(50, offsetC);
                                    fourthLine.setCharAt(60, characterD);
                                    fourthLine.setCharAt(61, offsetD);
                                    fourthLine.setCharAt(63, characterD);

                                    sixthLine.setCharAt(10, characterE);
                                    sixthLine.setCharAt(11, offsetE);
                                    sixthLine.setCharAt(13, offsetE);
                                    sixthLine.setCharAt(23, characterF);
                                    sixthLine.setCharAt(24, offsetF);
                                    sixthLine.setCharAt(26, characterF);
                                    sixthLine.setCharAt(36, characterG);
                                    sixthLine.setCharAt(37, offsetG);
                                    sixthLine.setCharAt(39, offsetG);


                                    String repeatString = "REP*" + String.valueOf(repeats / 2);

                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);

                                    writerTC.write(repeatString);
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(firstLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(secondLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(thirdLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(fourthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(fifthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(sixthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();

                                    writerTC2X.write(repeatString);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(firstLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(secondLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(thirdLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(fourthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(fifthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(sixthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();

                                    writer440.write(repeatString);
                                    writer440.write(System.lineSeparator());
                                    writer440.write(firstLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(secondLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(thirdLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fourthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fifthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(sixthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();
                                }

                                if (lastLine.length() == 8) {
                                    is8Color = true;
                                    //     System.out.println(repeats);
                                    char characterA = lastLine.charAt(0);
                                    char characterB = lastLine.charAt(1);
                                    char characterC = lastLine.charAt(2);
                                    char characterD = lastLine.charAt(3);
                                    char characterE = lastLine.charAt(4);
                                    char characterF = lastLine.charAt(5);
                                    char characterG = lastLine.charAt(6);
                                    char characterH = lastLine.charAt(7);
                                    char carrierA = carriers.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char carrierB = carriers.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char carrierC = carriers.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char carrierD = carriers.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char carrierE = carriers.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char carrierF = carriers.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                    char carrierG = carriers.get(colors.indexOf(String.valueOf(characterG))).charAt(0);
                                    char carrierH = carriers.get(colors.indexOf(String.valueOf(characterH))).charAt(0);
                                    char offsetA = offsets.get(colors.indexOf(String.valueOf(characterA))).charAt(0);
                                    char offsetB = offsets.get(colors.indexOf(String.valueOf(characterB))).charAt(0);
                                    char offsetC = offsets.get(colors.indexOf(String.valueOf(characterC))).charAt(0);
                                    char offsetD = offsets.get(colors.indexOf(String.valueOf(characterD))).charAt(0);
                                    char offsetE = offsets.get(colors.indexOf(String.valueOf(characterE))).charAt(0);
                                    char offsetF = offsets.get(colors.indexOf(String.valueOf(characterF))).charAt(0);
                                    char offsetG = offsets.get(colors.indexOf(String.valueOf(characterG))).charAt(0);
                                    char offsetH = offsets.get(colors.indexOf(String.valueOf(characterH))).charAt(0);

                                    if (String.valueOf(fullLine.charAt(0)).equals("X")) {
                                        isX = true;
                                        repeats += 2;
                                    }

                                    // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sintralNew, true), "UTF-8"));

                                    StringBuilder firstLine = new StringBuilder(eightColorA);
                                    String secondLine = eightColorB;
                                    StringBuilder thirdLine = new StringBuilder(eightColorC);
                                    StringBuilder fourthLine = new StringBuilder(eightColorD);
                                    String fifthLine = eightColorE;
                                    StringBuilder sixthLine = new StringBuilder(eightColorF);

                                    firstLine.setCharAt(14, characterA);
                                    firstLine.setCharAt(15, offsetA);
                                    firstLine.setCharAt(20, offsetA);
                                    firstLine.setCharAt(34, characterB);
                                    firstLine.setCharAt(35, offsetB);
                                    firstLine.setCharAt(37, characterB);
                                    firstLine.setCharAt(47, characterC);
                                    firstLine.setCharAt(48, offsetC);
                                    firstLine.setCharAt(50, offsetC);
                                    firstLine.setCharAt(60, characterD);
                                    firstLine.setCharAt(61, offsetD);
                                    firstLine.setCharAt(63, characterD);
                                    firstLine.setCharAt(76, carrierA);
                                    firstLine.setCharAt(78, carrierB);
                                    firstLine.setCharAt(80, carrierC);
                                    firstLine.setCharAt(82, carrierD);
                                    firstLine.setCharAt(84, carrierE);
                                    firstLine.setCharAt(86, carrierF);
                                    firstLine.setCharAt(88, carrierG);
                                    firstLine.setCharAt(90, carrierH);

                                    thirdLine.setCharAt(10, characterE);
                                    thirdLine.setCharAt(11, offsetE);
                                    thirdLine.setCharAt(13, offsetE);
                                    thirdLine.setCharAt(23, characterF);
                                    thirdLine.setCharAt(24, offsetF);
                                    thirdLine.setCharAt(26, characterF);
                                    thirdLine.setCharAt(36, characterG);
                                    thirdLine.setCharAt(37, offsetG);
                                    thirdLine.setCharAt(39, offsetG);
                                    thirdLine.setCharAt(49, characterH);
                                    thirdLine.setCharAt(50, offsetH);
                                    thirdLine.setCharAt(52, characterH);


                                    fourthLine.setCharAt(14, characterA);
                                    fourthLine.setCharAt(15, offsetA);
                                    fourthLine.setCharAt(20, offsetA);
                                    fourthLine.setCharAt(34, characterB);
                                    fourthLine.setCharAt(35, offsetB);
                                    fourthLine.setCharAt(37, characterB);
                                    fourthLine.setCharAt(47, characterC);
                                    fourthLine.setCharAt(48, offsetC);
                                    fourthLine.setCharAt(50, offsetC);
                                    fourthLine.setCharAt(60, characterD);
                                    fourthLine.setCharAt(61, offsetD);
                                    fourthLine.setCharAt(63, characterD);

                                    sixthLine.setCharAt(10, characterE);
                                    sixthLine.setCharAt(11, offsetE);
                                    sixthLine.setCharAt(13, offsetE);
                                    sixthLine.setCharAt(23, characterF);
                                    sixthLine.setCharAt(24, offsetF);
                                    sixthLine.setCharAt(26, characterF);
                                    sixthLine.setCharAt(36, characterG);
                                    sixthLine.setCharAt(37, offsetG);
                                    sixthLine.setCharAt(39, offsetG);
                                    sixthLine.setCharAt(49, characterH);
                                    sixthLine.setCharAt(50, offsetH);
                                    sixthLine.setCharAt(52, characterH);


                                    String repeatString = "REP*" + String.valueOf(repeats / 2);
                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);

                                    writerTC.write(repeatString);
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(firstLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(secondLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(thirdLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(fourthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(fifthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(sixthLine.toString());
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();

                                    writerTC2X.write(repeatString);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(firstLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(secondLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(thirdLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(fourthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(fifthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(sixthLine.toString());
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();

                                    writer440.write(repeatString);
                                    writer440.write(System.lineSeparator());
                                    writer440.write(firstLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(secondLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(thirdLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fourthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(fifthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write(sixthLine.toString());
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();
                                }

                                repeats = 1;

                                if (String.valueOf(fullLine.charAt(0)).equals("z")) {

                                    for(int skips=0;skips<40;skips++){
                                        line=bufferedReader.readLine();
                                        System.out.println(line);
                                        if(String.valueOf(line.charAt(5)).equals("$")){
                                            skips--;
                                        }
                                    }

                                    StringBuilder pers2 = new StringBuilder(JA2Reps);

                                    pers2.setCharAt(9, '2');
                                    pers2.setCharAt(10, '0');

                                    for (int templateLine = 124; templateLine < 150; templateLine++) {
                                        writerTC = new FileWriter(sintralNewTC, true);
                                        writerTC2X = new FileWriter(sintralNewTC2X, true);
                                        writer440 = new FileWriter(sintralNew440, true);
                                        String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(templateLine);
                                        writerTC.write(currentTemplateLine);
                                        writerTC.write(System.getProperty("line.separator"));
                                        writerTC.flush();
                                        writerTC.close();
                                        writerTC2X.write(currentTemplateLine);
                                        writerTC2X.write(System.getProperty("line.separator"));
                                        writerTC2X.flush();
                                        writerTC2X.close();
                                        writer440.write(currentTemplateLine);
                                        writer440.write(System.getProperty("line.separator"));
                                        writer440.flush();
                                        writer440.close();
                                    }



                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);
                                    writer440.write(pers2.toString());
                                    writer440.write(System.getProperty("line.separator"));
                                    writer440.flush();
                                    writer440.close();
                                    writerTC2X.write("REP*10");
                                    writerTC2X.write(System.getProperty("line.separator"));
                                    writerTC2X.flush();
                                    writerTC2X.close();
                                    writerTC.write(pers2.toString());
                                    writerTC.write(System.getProperty("line.separator"));
                                    writerTC.flush();
                                    writerTC.close();

                                    writerTC = new FileWriter(sintralNewTC, true);
                                    writerTC2X = new FileWriter(sintralNewTC2X, true);
                                    writer440 = new FileWriter(sintralNew440, true);

                                    writerTC2X.write(tcPersA);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write(tcPersB);
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("REPEND");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.write("JA1=??");
                                    writerTC2X.write(System.lineSeparator());
                                    writerTC2X.flush();
                                    writerTC2X.close();

                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(152));
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(153));
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("REPEND");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.write("JA1=??");
                                    writerTC.write(System.lineSeparator());
                                    writerTC.flush();
                                    writerTC.close();

                                    writer440.write(System.lineSeparator());
                                    writer440.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(152));
                                    writer440.write(System.lineSeparator());
                                    writer440.write(Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(153));
                                    writer440.write(System.lineSeparator());
                                    writer440.write("REPEND");
                                    writer440.write(System.lineSeparator());
                                    writer440.write("JA1=??");
                                    writer440.write(System.lineSeparator());
                                    writer440.flush();
                                    writer440.close();

                                }

                            } else {
                                repeats++;
                            }

                        }

                        writerTC = new FileWriter(sintralNewTC, true);
                        writerTC2X = new FileWriter(sintralNewTC2X, true);
                        writer440 = new FileWriter(sintralNew440, true);

                        for (int templateLine = 163; templateLine < 250; templateLine++) {
                            if (templateLine == 212) {
                                writerTC.write(" 945    YG:8/12345678;");
                                writerTC.write(System.getProperty("line.separator"));
                                writerTC2X.write(" 945    YG:8/12233445678;");
                                writerTC2X.write(System.getProperty("line.separator"));
                                writer440.write(" 945    YG:8/12345678;");
                                writer440.write(System.getProperty("line.separator"));
                            } else {
                                String currentTemplateLine = Files.readAllLines(Paths.get("C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/Custom Sintrals/Templates/templateBirdseye.txt")).get(templateLine);
                                writerTC.write(currentTemplateLine);
                                writerTC.write(System.getProperty("line.separator"));
                                writerTC2X.write(currentTemplateLine);
                                writerTC2X.write(System.getProperty("line.separator"));
                                writer440.write(currentTemplateLine);
                                writer440.write(System.getProperty("line.separator"));

                            }
                        }
                        writerTC.flush();
                        writerTC.close();
                        writerTC2X.flush();
                        writerTC2X.close();
                        writer440.flush();
                        writer440.close();
         //   System.out.println(num);
                        if(num==4){
                            writerTC = new FileWriter(sintralNewTC, true);
                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                            writer440 = new FileWriter(sintralNew440, true);
                            writerTC.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 993    END");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 993    END");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 993    END");
                            writer440.write(System.getProperty("line.separator"));
                        }
                        else if(num==5){
                            writerTC = new FileWriter(sintralNewTC, true);
                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                            writer440 = new FileWriter(sintralNew440, true);
                            writerTC.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 993    << S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 994    >> S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 995    END");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 993    << S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 994    >> S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 995    END");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 993    << S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 994    >> S:DI.(2)-D.I(2);\t\t\tY:6;\t\t\tWMI=11\t\tSX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 995    END");
                            writer440.write(System.getProperty("line.separator"));

                        }
                        else if(num==6){
                            writerTC = new FileWriter(sintralNewTC, true);
                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                            writer440 = new FileWriter(sintralNew440, true);
                            writerTC.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 995    END");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 995    END");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7;\t\tWMI=11\t\tSX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 995    END");
                            writer440.write(System.getProperty("line.separator"));

                        }
                        else if(num==7){
                            writerTC = new FileWriter(sintralNewTC, true);
                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                            writer440 = new FileWriter(sintralNew440, true);
                            writerTC.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI./DI.-D.I;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 994    >> S:D.I(2)-DI.(2)/DI.-D.I/D.I-DI.;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 995    END");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI./DI.-D.I;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 994    >> S:D.I(2)-DI.(2)/DI.-D.I/D.I-DI.;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 995    END");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI./DI.-D.I;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 994    >> S:D.I(2)-DI.(2)/DI.-D.I/D.I-DI.;\t\tY:6/7/1;\t\tWMI=11\t\tSX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 995    END");
                            writer440.write(System.getProperty("line.separator"));
                        }
                        else if(num==8){
                            writerTC = new FileWriter(sintralNewTC, true);
                            writerTC2X = new FileWriter(sintralNewTC2X, true);
                            writer440 = new FileWriter(sintralNew440, true);
                            writerTC.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC.write(" 995    END");
                            writerTC.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writerTC2X.write(" 995    END");
                            writerTC2X.write(System.getProperty("line.separator"));
                            writer440.write(" 991    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 992    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:2/3/4/5;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 993    << S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 994    >> S:DI.(2)-D.I(2)/D.I-DI.;\t\t\tY:6/7/1/8;\tWMI=11\t\tSX SX SX SX");
                            writer440.write(System.getProperty("line.separator"));
                            writer440.write(" 995    END");
                            writer440.write(System.getProperty("line.separator"));

                        }

                        writerTC.flush();
                        writerTC.close();
                        writerTC2X.flush();
                        writerTC2X.close();
                        writer440.flush();
                        writer440.close();

                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    if (isX == false) {
                        JOptionPane.showMessageDialog(null, "I don't think X marks the spot...");
                        //return;
                    }
                }

            }
        });


        doAGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == doAGridButton) {
                    int returnVal = fc.showOpenDialog(Barcode.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();


                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(new File(String.valueOf(file)));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }


                        String[] color = new String[8];

                        color[0] = textField1.getText();
                        color[1] = textField2.getText();
                        color[2] = textField3.getText();

                        if (textField4.getText().isEmpty() != true) {
                            color[3] = textField4.getText();
                        } else {
                            color[3] = " ";
                        }

                        if (textField5.getText().isEmpty() != true) {
                            color[4] = textField5.getText();
                        } else {
                            color[4] = " ";
                        }

                        if (textField6.getText().isEmpty() != true) {
                            color[5] = textField6.getText();
                        } else {
                            color[5] = " ";
                        }

                        if (textField7.getText().isEmpty() != true) {
                            color[6] = textField7.getText();
                        } else {
                            color[6] = " ";
                        }

                        if (textField8.getText().isEmpty() != true) {
                            color[7] = textField8.getText();
                        } else {
                            color[7] = " ";
                        }

                        int[] rgb = new int[num];

                        for (int i = 0; i < num; i++) {

                            switch (color[i]) {
                                case ".":
                                    rgb[i] = period;
                                    break;
                                case "A":
                                    rgb[i] = A;
                                    break;
                                case "Y":
                                    rgb[i] = Y;
                                    break;
                                case "T":
                                    rgb[i] = T;
                                    break;
                                case "*":
                                    rgb[i] = star;
                                    break;
                                case "I":
                                    rgb[i] = I;
                                    break;
                                case "+":
                                    rgb[i] = plus;
                                    break;
                                case "B":
                                    rgb[i] = B;
                                    break;
                                case "G":
                                    rgb[i] = G;
                                    break;
                                case "H":
                                    rgb[i] = H;
                                    break;
                                case "O":
                                    rgb[i] = O;
                                    break;
                                case "W":
                                    rgb[i] = W;
                                    break;
                                case "Z":
                                    rgb[i] = Z;
                                    break;
                                case "X":
                                    rgb[i] = X;
                                    break;
                                case "N":
                                    rgb[i] = N;
                                    break;
                                case "S":
                                    rgb[i] = S;
                                    break;
                                case "E":
                                    rgb[i] = E;
                                    break;
                                case "K":
                                    rgb[i] = K;
                                    break;
                                case "L":
                                    rgb[i] = L;
                                    break;
                                case "M":
                                    rgb[i] = M;
                                    break;
                                case "P":
                                    rgb[i] = P;
                                    break;
                                case "Q":
                                    rgb[i] = Q;
                                    break;
                                case "a":
                                    rgb[i] = a;
                                    break;
                                case "y":
                                    rgb[i] = y;
                                    break;
                                case "t":
                                    rgb[i] = t;
                                    break;
                                case "i":
                                    rgb[i] = smalli;
                                    break;
                                case "b":
                                    rgb[i] = b;
                                    break;
                                case "g":
                                    rgb[i] = g;
                                    break;
                                case "h":
                                    rgb[i] = h;
                                    break;
                                case "o":
                                    rgb[i] = o;
                                    break;
                                case "w":
                                    rgb[i] = w;
                                    break;
                                case "z":
                                    rgb[i] = z;
                                    break;
                                case "e":
                                    rgb[i] = smalle;
                                    break;
                                case "k":
                                    rgb[i] = smallk;
                                    break;
                                case "l":
                                    rgb[i] = l;
                                    break;
                                case "m":
                                    rgb[i] = m;
                                    break;
                                case "p":
                                    rgb[i] = p;
                                    break;
                                case "q":
                                    rgb[i] = q;
                                    break;
                                default:
                                    //  throw new IllegalArgumentException("Invalid color: " + rgb[i]);
                            }
                        }

                        int[] funky = new int[8];
                        funky[0] = new Color(10, 10, 10).getRGB();
                        funky[1] = new Color(40, 40, 40).getRGB();
                        funky[2] = new Color(80, 80, 80).getRGB();
                        funky[3] = new Color(255, 255, 251).getRGB();
                        funky[4] = new Color(255, 255, 250).getRGB();
                        funky[5] = new Color(255, 255, 249).getRGB();
                        funky[6] = new Color(255, 255, 248).getRGB();
                        funky[7] = new Color(255, 255, 247).getRGB();

//System.out.println(color[4]);
                        int height = image.getHeight();
                        for (int x = 0; x < 473; x++) {
                            for (int y1 = 0; y1 < height; y1++) {
                                int colorIndex = 0;
                                while (colorIndex < num) {
                                    int current = image.getRGB(x, y1);
                                    if (current == rgb[colorIndex]) {
                                        image.setRGB(x, y1, funky[colorIndex]);
                                    }
                                    colorIndex++;
                                }

                            }
                        }

                        for (int x = 0; x < 473; x++) {
                            for (int y1 = 0; y1 < height; y1++) {
                                int colorIndex = 0;
                                while (colorIndex < num) {
                                    int current = image.getRGB(x, y1);
                                    if (current == funky[colorIndex]) {
                                        image.setRGB(x, y1, finalColor[colorIndex]);
                                    }
                                    colorIndex++;
                                }

                            }
                        }



                        ArrayList<Integer> colors = new ArrayList<>(8);
                        colors.add(period);
                        colors.add(A);
                        colors.add(Y);
                        colors.add(T);
                        colors.add(star);
                        colors.add(I);
                        colors.add(plus);
                        colors.add(B);

                        int[] finalColor = {G,H,O,W,Z,E,K,L};

                        ArrayList<Integer> offsets = new ArrayList<>(8);
                        offsets.add(G);
                        offsets.add(H);
                        offsets.add(O);
                        offsets.add(W);
                        offsets.add(Z);
                        offsets.add(E);
                        offsets.add(K);
                        offsets.add(L);

                        int w = image.getWidth();
                        int h = image.getHeight();


                        for (int i = 0; i < w; i++) {
                            for (int j = 0; j < h; j++) {
                                //  System.out.println(i);
                                // System.out.println(j);
                                int current = image.getRGB(i, j);
                                int index = colors.indexOf(current);
                                //System.out.println(index);
                                if(i==0||i%2==0){
                                    if(j==1||j%2!=0) {
                                        image.setRGB(i, j, offsets.get(index));
                                    }
                                }

                                if(i==1||i%2!=0){
                                    if(j==0||j%2==0)
                                        image.setRGB(i,j,offsets.get(index));
                                }

                            }
                        }


                        BufferedImage biggerImg = new BufferedImage(481, height, BufferedImage.TYPE_INT_ARGB);
                        BufferedImage blank = new BufferedImage(8, height, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = biggerImg.createGraphics();


                        for (int rows = 0; rows < height; rows++) {
                            for (int cols = 0; cols < 8; cols++) {
                                blank.setRGB(cols, rows, period);
                            }
                        }

                        g2d.drawImage(blank, 0, 0, null);
                        g2d.drawImage(image, 8, 0, null);

                        BufferedImage finalImage = new BufferedImage(biggerImg.getWidth(),
                                biggerImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                        finalImage.createGraphics().drawImage(biggerImg, 0, 0, Color.WHITE, null);

                        String filename = "C:/Users/Sitex.9.10.20.B/Desktop/Approved BMPs/Barcodes/GridDone.bmp";
                        File outputfile = new File(filename);
                        try {
                            ImageIO.write(finalImage, "bmp", outputfile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });



        combine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                if (e.getSource() == combine) {



                    int returnVal = fc.showOpenDialog(Barcode.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File filePattern = fc.getSelectedFile();

                        returnVal = fc.showOpenDialog(Barcode.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File fileGrid = fc.getSelectedFile();
                            BufferedReader brGrid = null;
                            BufferedReader brPattern = null;

                            String filename = fileGrid.getName();
                            String filepath = fileGrid.getPath();
                            String newFile = "Combined"+filepath;
                            File outputfile = new File(newFile);

                            try {
                                PrintWriter pw = new PrintWriter(outputfile);
                                brGrid = new BufferedReader(new FileReader(fileGrid));
                                String line = brGrid.readLine();
                                brPattern = new BufferedReader(new FileReader(filePattern));
                                String linePattern = brPattern.readLine();


                                    while(linePattern!=null) {
                                        pw.println(linePattern);
                                        linePattern = brPattern.readLine();
                                    }
                                while (line != null)
                                {
                                    pw.println(line);
                                    line = brGrid.readLine();

                                }


                                pw.flush();

                                // closing resources
                                brPattern.close();
                                brGrid.close();
                                pw.close();

                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }





                        }

                    }
                }
            }
        });
    }



    public static void main (String[] args) throws IOException{

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Barcode().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,900);
        frame.setVisible(true);

    }
}
