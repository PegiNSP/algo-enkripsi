import java.util.Scanner;

public class EnkripsiDekripsi {
    private static final int[][] kunci = {{4, 9}, {2, 5}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();

        String namaTerenkripsi = enkripsi(nama);
        System.out.println("Nama terenkripsi: " + namaTerenkripsi);

        String namaTerdekripsi = dekripsi(namaTerenkripsi);
        System.out.println("Nama terdekripsi: " + namaTerdekripsi);
    }

    private static String enkripsi(String nama) {
        StringBuilder hasil = new StringBuilder();

        for (int i = 0; i < nama.length(); i++) {
            char karakter = nama.charAt(i);

            if (Character.isLetter(karakter)) {
                int nilaiASCII = (int) karakter;
                int nilaiEnkripsi = (kunci[0][0] * (nilaiASCII - 'A') + kunci[0][1] * (nilaiASCII - 'A')) % 26;
                char karakterEnkripsi = (char) (nilaiEnkripsi + 'A');
                hasil.append(karakterEnkripsi);
            } else {
                hasil.append(karakter);
            }
        }

        return hasil.toString();
    }

    private static String dekripsi(String namaTerenkripsi) {
        StringBuilder hasil = new StringBuilder();

        int determinant = kunci[0][0] * kunci[1][1] - kunci[0][1] * kunci[1][0];
        int inverseDeterminant = 0;

        for (int i = 0; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                inverseDeterminant = i;
                break;
            }
        }

        for (int i = 0; i < namaTerenkripsi.length(); i++) {
            char karakter = namaTerenkripsi.charAt(i);

            if (Character.isLetter(karakter)) {
                int nilaiASCII = (int) karakter;
                int nilaiDekripsi = (inverseDeterminant * (nilaiASCII - 'A') + (-kunci[1][0] * (nilaiASCII - 'A'))) % 26;

                if (nilaiDekripsi < 0) {
                    nilaiDekripsi += 26;
                }

                char karakterDekripsi = (char) (nilaiDekripsi + 'A');
                hasil.append(karakterDekripsi);
            } else {
                hasil.append(karakter);
            }
        }

        return hasil.toString();
    }
}
