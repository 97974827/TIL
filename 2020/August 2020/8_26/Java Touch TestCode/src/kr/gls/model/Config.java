package kr.gls.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Config {
    private final StringProperty master_password;
    private final StringProperty gil_password;
    private final StringProperty admin_password;
    private final StringProperty device_addr;
    private final StringProperty card_price;
    private final StringProperty min_card_price;
    private final StringProperty bonus1;
    private final StringProperty bonus2;
    private final StringProperty bonus3;
    private final StringProperty bonus4;
    private final StringProperty bonus5;
    private final StringProperty bonus6;
    private final StringProperty bonus7;
    private final StringProperty bonus8;
    private final StringProperty bonus9;
    private final StringProperty bonus10;
    private final StringProperty shop_id;
    private final StringProperty manager_id;
    private final StringProperty version;
    private final StringProperty data_collect_state;
    private final StringProperty encrypt;

    public Config() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Config(String master_password, String gil_password, String admin_password, String device_addr, String card_price, String min_card_price, String bonus1,
                  String bonus2, String bonus3, String bonus4, String bonus5, String bonus6, String bonus7, String bonus8, String bonus9, String bonus10,
                  String shop_id, String manager_id, String version, String data_collect_state, String encrypt) {

        this.master_password = new SimpleStringProperty(master_password);
        this.gil_password = new SimpleStringProperty(gil_password);
        this.admin_password = new SimpleStringProperty(admin_password);
        this.device_addr = new SimpleStringProperty(device_addr);
        this.card_price = new SimpleStringProperty(card_price);
        this.min_card_price = new SimpleStringProperty(min_card_price);
        this.bonus1 = new SimpleStringProperty(bonus1);
        this.bonus2 = new SimpleStringProperty(bonus2);
        this.bonus3 = new SimpleStringProperty(bonus3);
        this.bonus4 = new SimpleStringProperty(bonus4);
        this.bonus5 = new SimpleStringProperty(bonus5);
        this.bonus6 = new SimpleStringProperty(bonus6);
        this.bonus7 = new SimpleStringProperty(bonus7);
        this.bonus8 = new SimpleStringProperty(bonus8);
        this.bonus9 = new SimpleStringProperty(bonus9);
        this.bonus10 = new SimpleStringProperty(bonus10);
        this.shop_id = new SimpleStringProperty(shop_id);
        this.manager_id = new SimpleStringProperty(manager_id);
        this.version = new SimpleStringProperty(version);
        this.encrypt = new SimpleStringProperty(encrypt);
        this.data_collect_state = new SimpleStringProperty(data_collect_state);
    }

    public String getMasterPassword() {
        return master_password.get();
    }

    public StringProperty masterPasswordProperty() {
        return master_password;
    }

    public void setMasterPassword(String master_password) {
        this.master_password.set(master_password);
    }

    public String getGilPassword() {
        return gil_password.get();
    }

    public StringProperty gilPasswordProperty() {
        return gil_password;
    }

    public void setGilPassword(String gil_password) {
        this.gil_password.set(gil_password);
    }

    public String getAdminPassword() {
        return admin_password.get();
    }

    public StringProperty adminPasswordProperty() {
        return admin_password;
    }

    public void setAdminPassword(String admin_password) {
        this.admin_password.set(admin_password);
    }

    public String getDeviceAddr() {
        return device_addr.get();
    }

    public StringProperty deviceAddrProperty() {
        return device_addr;
    }

    public void setDeviceAddr(String device_addr) {
        this.device_addr.set(device_addr);
    }

    public String getCardPrice() {
        return card_price.get();
    }

    public StringProperty cardPriceProperty() {
        return card_price;
    }

    public void setCardPrice(String card_price) {
        this.card_price.set(card_price);
    }

    public String getMinCardPrice() {
        return min_card_price.get();
    }

    public StringProperty minCardPriceProperty() {
        return min_card_price;
    }

    public void setMinCardPrice(String min_card_price) {
        this.min_card_price.set(min_card_price);
    }

    public String getBonus1() {
        return bonus1.get();
    }

    public StringProperty bonus1Property() {
        return bonus1;
    }

    public void setBonus1(String bonus1) {
        this.bonus1.set(bonus1);
    }

    public String getBonus2() {
        return bonus2.get();
    }

    public StringProperty bonus2Property() {
        return bonus2;
    }

    public void setBonus2(String bonus2) {
        this.bonus2.set(bonus2);
    }

    public String getBonus3() {
        return bonus3.get();
    }

    public StringProperty bonus3Property() {
        return bonus3;
    }

    public void setBonus3(String bonus3) {
        this.bonus3.set(bonus3);
    }

    public String getBonus4() {
        return bonus4.get();
    }

    public StringProperty bonus4Property() {
        return bonus4;
    }

    public void setBonus4(String bonus4) {
        this.bonus4.set(bonus4);
    }

    public String getBonus5() {
        return bonus5.get();
    }

    public StringProperty bonus5Property() {
        return bonus5;
    }

    public void setBonus5(String bonus5) {
        this.bonus5.set(bonus5);
    }

    public String getBonus6() {
        return bonus6.get();
    }

    public StringProperty bonus6Property() {
        return bonus6;
    }

    public void setBonus6(String bonus6) {
        this.bonus6.set(bonus6);
    }

    public String getBonus7() {
        return bonus7.get();
    }

    public StringProperty bonus7Property() {
        return bonus7;
    }

    public void setBonus7(String bonus7) {
        this.bonus7.set(bonus7);
    }

    public String getBonus8() {
        return bonus8.get();
    }

    public StringProperty bonus8Property() {
        return bonus8;
    }

    public void setBonus8(String bonus8) {
        this.bonus8.set(bonus8);
    }

    public String getBonus9() {
        return bonus9.get();
    }

    public StringProperty bonus9Property() {
        return bonus9;
    }

    public void setBonus9(String bonus9) {
        this.bonus9.set(bonus9);
    }

    public String getBonus10() {
        return bonus10.get();
    }

    public StringProperty bonus10Property() {
        return bonus10;
    }

    public void setBonus10(String bonus10) {
        this.bonus10.set(bonus10);
    }

    public String getShopId() {
        return shop_id.get();
    }

    public StringProperty shopIdProperty() {
        return shop_id;
    }

    public void setShopId(String shop_id) {
        this.shop_id.set(shop_id);
    }

    public String getManagerId() {
        return manager_id.get();
    }

    public StringProperty managerIdProperty() {
        return manager_id;
    }

    public void setManagerId(String manager_id) {
        this.manager_id.set(manager_id);
    }

    public String getVersion() {
        return version.get();
    }

    public StringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public String getEncrypt() {
        return encrypt.get();
    }

    public StringProperty EncryptProperty() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt.set(encrypt);
    }

    public String getDataCollectState() {
        return data_collect_state.get();
    }

    public StringProperty dataCollectStateProperty() {
        return data_collect_state;
    }

    public void setDataCollectState(String data_collect_state) {
        this.data_collect_state.set(data_collect_state);
    }
}
