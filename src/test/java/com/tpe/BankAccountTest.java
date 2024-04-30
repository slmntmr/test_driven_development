package com.tpe;

/*
        ***********************************
               Banka Hesap Uygulamasi
        ***********************************
        Uygulamada yazilacak metodlar:
            *   Hesap Olusturma
            *   Para Cekme ( Bakiye yetersiz kontrolu, Gunluk Cekim Limiti Kontrolu, Negatif Giris kontrolu )
            *   Para Yatirma ( Negatif Giris kontrolu )
            *   Hesap Gecmisi Alabilme kontrolu
 */ //!!! TDD aciklama ve GEREKSINIMLER


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    // Not: Hesap Olusturma Testleri **********************************
    @Test
    public void testCreateAccount(){
        BankAccount account = new BankAccount("12345", 0.0, 1000);
        assertNotNull(account);
        assertEquals("12345", account.getAccountNumber());
        assertEquals(0.0, account.getBalance(), 0.01); // delta : tolere edilecek deger
    }

    // Not: Para Yatirma Testleri ***************************************
    @Test
    public void testDeposit(){
        BankAccount account = new BankAccount("12345", 100.0, 1000);
        account.deposit(200.0);
        assertEquals(300.0, account.getBalance(), 0.01);
    }
    // negatif sayi girisi testi
    @Test
    public void testDepositNegativeAmount(){
        BankAccount account = new BankAccount("12345", 100.0, 1000);
        account.deposit(-100.0);
        assertEquals(100.0, account.getBalance(), 0.01);
    }


    // Not: Para cekme Testleri *****************************************
    @Test
    public void testWithdraw(){
        BankAccount account = new BankAccount("12345", 300.0, 1000);
        assertTrue(account.withdraw(200.0));
        assertEquals(100.0 , account.getBalance(), 0.01);
    }

    // yetersiz bakiye ile para cekme
    @Test
    public void testWithdrawInsufficientBalance(){
        BankAccount account = new BankAccount("12345", 100.0, 1000);
        assertFalse(account.withdraw(200.0));
    }
    // negatif deger ile para cekme
    @Test
    public void testWithdrawNegativeAmount(){
        BankAccount account = new BankAccount("12345", 100.0, 1000);
        assertFalse(account.withdraw(-100.0));
    }
    // gunluk para cekme limiti asiliyorsa
    @Test
    public void testWithdrawWithDailyLimit(){
        BankAccount account = new BankAccount("12345", 5000.0, 1000);
        assertTrue(account.withdraw(500.0)); // ilk cekim
        assertTrue(account.withdraw(500.0)); // ikinci cekim
        assertFalse(account.withdraw(100.0)); // ucuncu cekim
        assertEquals(4000.0, account.getBalance(), 0.01);
    }

    // Not: Hesap Gecmisi Testi *****************************************
    @Test
    public void testAccountHistory(){
        BankAccount account = new BankAccount("12345", 100.0, 1000);
        account.deposit(100.0); // bakiye = 200
        account.withdraw(50.0); // bakiye = 150
        assertEquals(2, account.getHistory().size());
    }
}