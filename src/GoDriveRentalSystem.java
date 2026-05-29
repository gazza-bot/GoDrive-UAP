import java.util.ArrayList;
import java.util.Scanner;

public class GoDriveRentalSystem {
    Scanner input = new Scanner(System.in);
    ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem() {
        this.daftarKendaraan = new ArrayList<>();
        this.daftarKendaraan.add(new Motor("MTR001", "YAMAHA NMAX 2022", 100000, "Matic"));
        this.daftarKendaraan.add(new Motor("MTR001", "YAMAHA VEGA ZR", 50000, "Manual"));
        this.daftarKendaraan.add(new Motor("MTR001", "YAMAHA JUPITER Z1", 60000, "Manual"));
        this.daftarKendaraan.add(new Motor("MTR001", "YAMAHA AEROX 2022", 110000, "Matic"));
        this.daftarKendaraan.add(new Motor("MTR001", "YAMAHA FILANO 2022", 90000, "Matic"));
        this.daftarKendaraan.add(new Mobil("MBL001", "HONDA JAZZ 2008", 350000, 4));
        this.daftarKendaraan.add(new Mobil("MBL002", "TOYOTA AVANZA", 450000, 6));
        this.daftarKendaraan.add(new Mobil("MBL003", "HONDA JAZZ 2008", 300000, 4));
        this.daftarKendaraan.add(new Mobil("MBL004", "MITSUBISHI PAJERO SPORT", 550000, 4));
        this.daftarKendaraan.add(new Mobil("MBL005", "DAIHATSU XENIA", 250000, 6));
    }

    public static void main(String[] args) throws Exception {
        GoDriveRentalSystem sys = new GoDriveRentalSystem();
        sys.menuMain();
    }

    public void tambahKendaraan() {
        System.out.print("Masukkan Jenis Kendaraan: ");
        String jenisKendaraan = input.nextLine();
        if (jenisKendaraan.equalsIgnoreCase("Motor")) {
            System.out.print("Masukkan kode kendaraan: ");
            String kode = input.nextLine();
            System.out.print("Masukkan nama kendaraan: ");
            String nama = input.nextLine();
            System.out.print("Masukkan harga sewa per hari: ");
            double hargaSewa = input.nextDouble();
            input.nextLine();
            System.out.print("Masukkan jenis transmisi: ");
            String jenisTransmisi = input.nextLine();
            this.daftarKendaraan.add(new Motor(kode, nama, hargaSewa, jenisTransmisi));
            System.out.printf("[INFO] Kendaraan Berhasil ditambahkan: %s (%s)\n", nama, kode);
        } else if (jenisKendaraan.equalsIgnoreCase("Mobil")) {
            System.out.print("Masukkan kode kendaraan: ");
            String kode = input.nextLine();
            System.out.print("Masukkan nama kendaraan: ");
            String nama = input.nextLine();
            System.out.print("Masukkan harga sewa per hari: ");
            double hargaSewa = input.nextDouble();
            input.nextLine();
            System.out.print("Masukkan jumlah kursi: ");
            int jumlahKursi = input.nextInt();
            input.nextLine();
            this.daftarKendaraan.add(new Mobil(kode, nama, hargaSewa, jumlahKursi));
            System.out.printf("[INFO] Kendaraan Berhasil ditambahkan: %s (%s)\n", nama, kode);
        } else {
            System.out.println("[INFO] ERROR: Jenis Kendaraan yang diinputkan harus Motor / Mobil!");
        }
    }

    public void tampilkanDaftarKendaraan() {
        int idx = 1;
        System.out.println("===== ARMADA GODRIVE RENTAL =====");
        for (Kendaraan kendaraan : daftarKendaraan) {
            System.out.print(idx + ". ");
            kendaraan.tampilInfo();
            idx++;
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa) throws KendaraanTidakTersediaException {
        System.out.print("Apakah Anda Member VIP (y/n): ");
        String VIP = input.nextLine();
        double biayaDasar = 0;
        double total = 0;
        double diskon = 0;
        double addOns = 0;
        double promo = 0;
        for (Kendaraan i : daftarKendaraan) {
            if (i.getKodeKendaraan().equals(kode)) {
                if (!i.isTersedia()) {
                    throw new KendaraanTidakTersediaException("Kendaraan dengan kode [" + kode
                            + "] gagal disewa. Karena Kendaraan tersebut sedang disewa atau kendaraan tersebut TIDAK ADA.");
                }
                i.setTersedia(false);
                if (i instanceof Mobil) {

                    biayaDasar = i.hitungBiayaDasar(lamaSewa);
                    addOns = 50000;
                    if (VIP.equals("y")) {
                        diskon = ((biayaDasar - addOns) * 0.1);
                    }

                    if (lamaSewa > 7) {
                        promo = (biayaDasar - addOns) * 0.05;
                    }

                    total = biayaDasar - diskon - promo;

                    System.out.println("=== TRANSAKSI SEWA GODRIVE ===");
                    System.out.println("Kendaraan Berhasil disewa!");
                    System.out.printf("Unit\t: %s (%s)\n", i.getNamaKendaraan(), i.getKodeKendaraan());
                    System.out.printf("Lama Sewa: %d hari\n", lamaSewa);
                    System.out.printf("Biaya dasar : Rp%,.0f", biayaDasar - addOns);
                    System.out.printf("\nTambahan Kursi (>5): Rp.%,.0f", addOns);
                    System.out.printf("\nDiskon VIP: -Rp%,.0f", diskon);
                    System.out.printf("\nDiskon Promo: -Rp%,.0f", promo);
                    System.out.println("\n------------------------------------");
                    System.out.printf("Total: Rp %,.0f\n", total);
                    // return total;
                } else if (i instanceof Motor) {
                    biayaDasar = i.hitungBiayaDasar(lamaSewa);
                    addOns = biayaDasar - (i.getHargaSewaPerHari() * lamaSewa);
                    if (VIP.equals("y")) {
                        diskon = ((biayaDasar - addOns) * 0.1);
                    }

                    if (lamaSewa > 7) {
                        promo = (biayaDasar - addOns) * 0.05;
                    }

                    total = biayaDasar - diskon - promo;

                    System.out.println("=== TRANSAKSI SEWA GODRIVE ===");
                    System.out.println("Kendaraan Berhasil disewa!");
                    System.out.printf("Unit\t: %s (%s)\n", i.getNamaKendaraan(), i.getKodeKendaraan());
                    System.out.printf("Lama Sewa: %d hari\n", lamaSewa);
                    System.out.printf("Biaya dasar : Rp. %,.0f\n", biayaDasar - addOns);
                    System.out.printf("Biaya Asuransi Per Hari: Rp. %,.0f\n", addOns);
                    System.out.printf("Diskon VIP : -Rp. %,.0f\n", diskon);
                    System.out.printf("Diskon Promo : -Rp. %,.0f\n", promo);
                    System.out.printf("Total: Rp. %,.0f\n", total);
                }
            } else {

            }
        }
    }

    public void kembalikanKendaraan(String kode) throws KendaraanTidakTersediaException {
        for (Kendaraan i : daftarKendaraan) {
            if(i.getKodeKendaraan().equals(kode)){
                if (i.isTersedia() == false) {
                    i.setTersedia(true);
                    System.out.println("[INFO] Kendaraan dengan kode " + kode + " Sudah Dikembalikan, Status : Tersedia");
                } else {
                    throw new KendaraanTidakTersediaException(
                            "Kendaraan dengan kode [" + kode + "] Berstatus Tersedia. Pengembalian Tidak Valid!");
                }
            }
        }
    }

    public void menuMain() throws KendaraanTidakTersediaException {
        do {

            System.out.println("====== MENU GO DRIVE RENTAL SYSTEM =====");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan daftar armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");

            int pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1 -> {
                    this.tambahKendaraan();
                }

                case 2 -> {
                    this.tampilkanDaftarKendaraan();
                }

                case 3 -> {
                    System.out.print("Masukkan Kode Kendaraan yang ingin disewa: ");
                    String kode = input.nextLine();
                    System.out.print("Masukkan durasi sewa (dalam hari): ");
                    int lamaSewa = input.nextInt();
                    input.nextLine();
                    this.sewaKendaraan(kode, lamaSewa);
                }

                case 4 -> {
                    System.out.print("Masukkan Kode Kendaraan yang ingin dikembalikan: ");
                    String kode = input.nextLine();
                    this.kembalikanKendaraan(kode);
                }

                case 5 -> {
                    System.exit(0);
                }
            }
        } while (true);
    }
}
