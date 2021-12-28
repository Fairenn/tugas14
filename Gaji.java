public class Gaji implements PTABC{
    
    public String noPegawai, namaPegawai, jabatan;
    
    @Override
    public void NoPegawai() {
        System.out.println("Masukkan nomor pegawai: ");
        
    }
    @Override
    public void NamaPegawai() {
        System.out.println("\nMasukkan nama pegawai: ");
        
    }
    @Override
    public void Jabatan() {
        System.out.println("\nMasukkan jabatan: ");
        
    }
    
}
