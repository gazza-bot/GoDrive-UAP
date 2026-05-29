public class Mobil extends Kendaraan {
    private int jumlahKursi;
    public Mobil(String kode, String nama, double harga, int jumlahKursi){
        super(kode, nama, harga, true);
        this.jumlahKursi = jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi) {
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi(){
        return this.jumlahKursi;
    }

    @Override
    public void tampilInfo(){
        System.out.printf("[MOBIL] Kode: %s  | Nama: %s \t| Kursi: %d | Tarif: Rp%,.0f/hari | Status: %s\n", this.getKodeKendaraan(),this.getNamaKendaraan(),this.jumlahKursi, this.getHargaSewaPerHari(), this.isTersedia() ? "Tersedia" : "Tidak Tersedia" );
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa){
        if(jumlahKursi > 5){
            return (this.getHargaSewaPerHari() * lamaSewa) + 50000;
        }else{
            return (this.getHargaSewaPerHari() * lamaSewa);
        }
    }
}
