abstract class Kendaraan {
    private String kodeKendaraan;
    private String namaKendaraan;
    private double hargaSewaPerHari;
    private boolean isTersedia;

    public Kendaraan(String kode, String nama, double harga, boolean tersedia) {
        this.kodeKendaraan = kode;
        this.namaKendaraan = nama;
        this.hargaSewaPerHari = harga;
        this.isTersedia = tersedia;
    }

    public void setKodeKendaraan(String kode) {
        this.kodeKendaraan = kode;
    }

    public String getKodeKendaraan() {
        return this.kodeKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getNamaKendaraan() {
        return this.namaKendaraan;
    }

    public void setHargaSewa(double harga) {
        this.hargaSewaPerHari = harga;
    }

    public double getHargaSewaPerHari() {
        return this.hargaSewaPerHari;
    }


    public void setTersedia(boolean tersedia) {
        this.isTersedia = tersedia;
    }

    public boolean isTersedia() {
        return this.isTersedia;
    }

    public abstract void tampilInfo();

    public abstract double hitungBiayaDasar(int lamaSewa);
}
