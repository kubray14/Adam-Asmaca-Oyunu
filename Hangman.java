import java.util.Random;
import java.util.Scanner;


/**
 *HANGMAN
 *"Adam asmaca" oyun kodlarını oluşturma
 * 1. Ödev
 * 30.11.2018
 * @author Kübra - k.yildirim1499@gmail.com
 */
public class Hangman {
    public static String menu() {

        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Oyuna başlamak için (B);");
        System.out.println("Sözlüğe yeni kelime eklemek için (Y);");
        System.out.println("Hak adedini arttırmak için (H)");
        System.out.println("Çıkmak için (C) tuşlayınız.");
        System.out.println("Seçim ?: ");
        String selection = input.nextLine();

        return selection;
    }

    //YAN MENÜ METODU
    public static String menu2() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ana menüye dönmek için (A)");
        System.out.println("Çıkış yapmak için (C) tuşlayınız");
        System.out.println("Seçim ?:");
        String selection = input.nextLine();

        return selection;
    }

    //PARAMETRE OLARAK ALINAN BİR STRING ARRAYINDEN RANDOM KELİME DÖNDÜRMEK İÇİN;
    public static String randomWord(String[] wordArray) {
        Random r = new Random();
        int number = 0;

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] != null) {
                number++;
            }
        }
        int wordIndex = r.nextInt(number);
        String word = wordArray[wordIndex];

        return word;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String Y = "Y";
        String B = "B";
        String C = "C";
        String H = "H";
        String A = "A";
       
        int number = 7;
        //Kullanıcının oyundan tamamen çıkmak isteyip istemediğini kontrol etmek için;
        int controlValue = 0;
        //bir sonraki oyunda extra harf gösterilip gösterilmeyeceğıni kontrol etmek için;
        int checkPrize = 0;
        int chance = 3;
        String[] wordsArray = {"matematik", "program", "bilgisayar", "samimiyet",
            "mahcubiyet", "klasik", "java"};
        for (;;) {

            String selection = menu();
            int chance2 = chance;
            int temporary = chance2;
            //Oyuna başlanmak isteniyor ise;
            if (selection.charAt(0) == B.charAt(0)) {
                String word = randomWord(wordsArray);

                System.out.println("****************");
                System.out.println("OYUN BAŞLAMIŞTIR");
                System.out.println("Bulmanız gereken kelime " + word.length() + " harftir.");
                System.out.println(chance2 + " adet tahmin hakkınız vardır.");

             
                char selectWord[] = new char[word.length()];
                char[] secretarray = new char[word.length()];
                for (int i = 0; i < word.length(); i++) {
                    selectWord[i] = word.charAt(i);
                    secretarray[i] = '_';
                }

                if (checkPrize == 1) {
                    Random r = new Random();
                    char extra = selectWord[r.nextInt(word.length())];
                    for (int j = 0; j < word.length(); j++) {
                        if (selectWord[j] == extra) {
                            secretarray[j] = extra;
                        }

                    }
                    for (int j = 0; j < word.length(); j++) {
                        System.out.print(secretarray[j] + " ");

                    }

                } else {
                    for (int i = 0; i < word.length(); i++) {
                        System.out.print(secretarray[i] + " ");

                    }
                }

                // BOUND HATASI OLMAMASI Ä°Ã‡Ä°N SONSUZ BÄ°R DÃ–NGÃœ KULLANDIM.
                while (true) {

                    System.out.println("");
                    System.out.println("Bir harf giriniz; ");
                    //HARF DENEMESÄ°NÄ° KULLANICIDAN ALMA
                    char guess = input.next().charAt(0);
                    int exist1 = 0;
                    //HARFÄ°N KELÄ°MEDE OLUP OLMADIÄ�INI KONTROL ETME VE YERÄ°NE YAZMA
                    for (int j = 0; j < word.length(); j++) {
                        if (selectWord[j] == guess) {
                            secretarray[j] = selectWord[j];
                            exist1++;

                        }
                    }

                    if (exist1 != 0) {
                        for (int j = 0; j < word.length(); j++) {
                            System.out.print(secretarray[j] + " ");
                        }
                        System.out.println("   (" + chance2 + ") adet hakkınız vardır.");

                    } else if (exist1 == 0) {
                        chance2--;
                        System.out.println(guess + " harfi kelimede yoktur." + chance2 + " adet hakkınız kaldı.");

                    }
                    if (chance2 == 0) {
                        System.out.println("");
                        System.out.println("Hakkınız kalmamıştır. Kaybettiniz..");
                        System.out.println("");
                        String temp = menu2();
                        if (A.charAt(0) == temp.charAt(0)) {
                            break;
                        }
                        if (C.charAt(0) == temp.charAt(0)) {
                            controlValue = 1;
                            break;

                        }

                    }

                    int temp = 0;
                    for (int j = 0; j < word.length(); j++) {

                        if (secretarray[j] == selectWord[j]) {
                            temp++;
                        }
                    }
                    if (temp == word.length()) {
                        System.out.println("***************************");
                        if (chance2 == temporary) {
                            System.out.println("TEBRİKLER KELİMEYİ HİÇ HATA YAPMADAN BULDUNUZ !");

                            checkPrize = 1;
                            System.out.println(" **Bir sonraki oyunda ödül olarak bir harf gösterilecektir :) ");
                            System.out.println("");
                        } else {
                            System.out.println("TEBRİKLER KELİMEYİ BULDUNUZ !");
                            checkPrize = 0;
                        }
                        String temp2 = menu2();
                        if (temp2.charAt(0) == A.charAt(0)) {
                            break;

                        }
                        if (temp2.charAt(0) == C.charAt(0)) {
                            controlValue = 1;
                            break;

                        }
                    }
                }
                if (controlValue == 1) {
                    System.out.println("");
                    System.out.println("Çıkış yapılıyor..");
                    break;
                }

            }
            if (selection.charAt(0) == Y.charAt(0)) {
                Scanner scan = new Scanner(System.in);

                String newDictionary[] = new String[500];
                for (int i = 0; i < wordsArray.length; i++) {
                    newDictionary[i] = wordsArray[i];
                }
                if (number >= newDictionary.length) {
                    System.out.println("");
                    System.out.println("Üzgünüz.. Sözlükteki kelime kotamız dolmuştur.");
                    System.out.println("");
                } else {
                    System.out.println("Eklemek istediğiniz kelimeyi giriniz:");
                    String newWord = scan.nextLine();
                    newDictionary[number++] = newWord;
                    System.out.println("Kelimeniz sözlüğe eklenmiştir.");
                    wordsArray = newDictionary;
                    System.out.println("");
                }
                String selection2 = menu2();
                if (selection2.charAt(0) == C.charAt(0)) {
                    System.out.println("Çıkış yapılıyor..");
                    break;
                }
            }
            //HakkÄ±n arttÄ±rÄ±lmasÄ± istenmiÅŸ ise;
            if (selection.charAt(0) == H.charAt(0)) {
                chance++;
                System.out.println("Hakkınız bir arttırılmıştır ve " + chance + " adet olmuştur.");
            }
            //Direk Ã§Ä±kÄ±ÅŸ yapÄ±lmak istenmiÅŸ ise;
            if (selection.charAt(0) == C.charAt(0)) {
                System.out.println("Çıkış yapılıyor..");
                break;

            }

        }
    }
}
