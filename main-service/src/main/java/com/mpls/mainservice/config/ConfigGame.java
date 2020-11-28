package com.mpls.mainservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "config.game")
public class ConfigGame {
    private BigDecimal growthOfEcology;
    private BigDecimal fallingEcologyPerCreateWeapon;
    private BigDecimal fallingEcologyPerUsedWeapon;
    private BigDecimal baseEcology;
    private BigDecimal maxEcology;
    private BigDecimal minValueEcology;
    private BigDecimal negativeEffectOfSanctions;
    private BigDecimal priceWeapon;
    private BigDecimal priceUpdateCity;
    private BigDecimal priceUpdateEcology;
    private BigDecimal priceWeaponTechnology;
    private BigDecimal priceCreateShield;
    private BigDecimal defaultGoldIncome;
    private BigDecimal defaultCityUpgrade;
    private BigDecimal growthCityUpgrade;
    private BigDecimal divideByOneHundred;
    private BigDecimal moneyStatic;
    private Map<Long, CountryList> countryList;

    public ConfigGame() {
        this.countryList = new HashMap<>();
        this.countryList.put(1L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg", "Україна", "#e4de0c",
                        createCity(
                                Arrays.asList(
                                        createCity("https://img.the-village.com.ua/the-village.com.ua/post_image-image/xmC-qt_8CblIF0DajSeGaQ.jpg", "Київ", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/%D0%A5%D0%B0%D1%80%D0%BA%D1%96%D0%B2%2C_%D0%BC%D0%B0%D0%B9%D0%B4._%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B8%D1%82%D1%83%D1%86%D1%96%D1%97_24%2C_%D0%92%D0%BE%D0%BB%D0%B7%D1%8C%D0%BA%D0%BE-%D0%9A%D0%B0%D0%BC%D1%81%D1%8C%D0%BA%D0%B8%D0%B9_%D0%B1%D0%B0%D0%BD%D0%BA.jpg/1200px-%D0%A5%D0%B0%D1%80%D0%BA%D1%96%D0%B2%2C_%D0%BC%D0%B0%D0%B9%D0%B4._%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B8%D1%82%D1%83%D1%86%D1%96%D1%97_24%2C_%D0%92%D0%BE%D0%BB%D0%B7%D1%8C%D0%BA%D0%BE-%D0%9A%D0%B0%D0%BC%D1%81%D1%8C%D0%BA%D0%B8%D0%B9_%D0%B1%D0%B0%D0%BD%D0%BA.jpg", "Харків", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/%D0%91%D1%83%D0%B4%D1%96%D0%B2%D0%BB%D1%8F_%D0%B7%D0%B0%D0%BB%D1%96%D0%B7%D0%BD%D0%B8%D1%87%D0%BD%D0%BE%D0%B3%D0%BE_%D0%B2%D0%BE%D0%BA%D0%B7%D0%B0%D0%BB%D1%83_01.jpg/1200px-%D0%91%D1%83%D0%B4%D1%96%D0%B2%D0%BB%D1%8F_%D0%B7%D0%B0%D0%BB%D1%96%D0%B7%D0%BD%D0%B8%D1%87%D0%BD%D0%BE%D0%B3%D0%BE_%D0%B2%D0%BE%D0%BA%D0%B7%D0%B0%D0%BB%D1%83_01.jpg", "Одеса", ""),
                                        createCity("https://etnosvit.com/wp-content/uploads/2019/03/trips-lvov.jpg", "Львів", "")
                                )
                        )
                )
        );
        this.countryList.put(2L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/b/ba/Flag_of_Germany.svg", "Німеччина", "#797171",
                        createCity(
                                Arrays.asList(
                                        createCity("https://publish.com.ua/images/2020/03/25/cover_original[1]_large.jpg", "Берлін", ""),
                                        createCity("https://www.eurotopics.net/cache/images/2/208522-5x3-facebook.jpg?92A41-1597997085", "Франкфурт", ""),
                                        createCity("https://incomartour.com.ua/mediafiles/images/ccimage-shutterstock_566911099web.jpg", "Мюнхен", ""),
                                        createCity("https://content.r9cdn.net/rimg/dimg/3a/b8/ae865eb4-city-32131-16e59d6187f.jpg?crop=true&width=1000&height=600&xhint=1328&yhint=939", "Гамбург", "")
                                )
                        )
                )
        );
        this.countryList.put(3L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Flag_of_Israel.svg/1280px-Flag_of_Israel.svg.png", "Ізраїль", "#6c9abf",
                        createCity(
                                Arrays.asList(
                                        createCity("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/24/61/96/western-wall-temple-mount.jpg?w=1000&h=600&s=1", "Єрусалим", ""),
                                        createCity("https://s0.rbk.ru/v6_top_pics/media/img/9/69/755368334597699.png", "Тель-Авів", ""),
                                        createCity("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/33/fb/82/eilat.jpg?w=1100&h=600&s=1", "Ейлат", ""),
                                        createCity("https://ic.pics.livejournal.com/uritsk/19436209/669426/669426_original.jpg", "Хайфа", "")
                                )
                        )
                )
        );
        this.countryList.put(4L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Flag_of_Russia.svg/800px-Flag_of_Russia.svg.png", "Росія", "#140c98",
                        createCity(
                                Arrays.asList(
                                        createCity("https://russkiymir.ru/upload/medialibrary/a11/a119490a5241632e252c36ed44bde674.jpg", "Москва", ""),
                                        createCity("http://riara.com.ua/wp-content/uploads/2019/08/794067a6e76da69534cb642df8e0f8be.jpg", "Іжевськ", ""),
                                        createCity("https://www.onetwotrip.com/ru/blog/wp-content/uploads/2018/02/vladivostok.jpg", "Владивосток", ""),
                                        createCity("https://cdn2.tu-tu.ru/image/pagetree_node_data/1/e7bc381619af4dd182ff59e7daa87f9b/", "Екатеринбург", "")
                                )
                        )
                )
        );
        this.countryList.put(5L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/9/9e/Flag_of_Japan.svg", "Японія", "#ec0bc6",
                        createCity(
                                Arrays.asList(
                                        createCity("https://airlife.ua/wp-content/uploads/tokio.jpg", "Токіо", ""),
                                        createCity("https://youtravel.life/upload/iblock/4dc/4dc0b4b463470b5b50ba7f47c7c76689.jpg", "Кіото", ""),
                                        createCity("https://cf.bstatic.com/images/hotel/max1024x768/254/254409161.jpg", "Осака", ""),
                                        createCity("https://static.tonkosti.ru/tonkosti/table_img/g192/8686/164385196.jpg", "Іокагама", "")
                                )
                        )
                )
        );
        this.countryList.put(6L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/f/fa/Flag_of_the_People%27s_Republic_of_China.svg", "Китай", "#f13030",
                        createCity(
                                Arrays.asList(
                                        createCity("https://wikiway.com/upload/hl-photo/5b9/bba/pekin_9.jpg", "Пекін", ""),
                                        createCity("https://youtravel.life/upload/iblock/5ad/5ad562fdf70e2560d06c6f6aa5fd1f65.jpg", "Шанхай", ""),
                                        createCity("https://cf.bstatic.com/images/hotel/max1024x768/174/174492074.jpg", "Гуанчжоу", ""),
                                        createCity("https://cdnimg.rg.ru/i/gallery/0813221a/1_6ce69abe.jpg", "Ухань", "")
                                )
                        )
                )
        );
        this.countryList.put(7L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/5/51/Flag_of_North_Korea.svg", "Північна Корея", "#da742b",
                        createCity(
                                Arrays.asList(
                                        createCity("https://cdnimg.rg.ru/i/gallery/8e2c595f/9_03c20000.jpg", "Пхеньян", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/5/51/Flag_of_North_Korea.svg", "Кесон", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/5/51/Flag_of_North_Korea.svg", "Вонсан", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/5/51/Flag_of_North_Korea.svg", "Хесан", "")
                                )
                        )
                )
        );
        this.countryList.put(8L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/a/ae/Flag_of_the_United_Kingdom.svg", "Велика Британія", "#774405",
                        createCity(
                                Arrays.asList(
                                        createCity("https://34travel.me/media/upload/images/2017/august/london-shevchuk/4.jpg", "Лондон", ""),
                                        createCity("https://wikiway.com/upload/hl-photo/16d/07c/birmingem_1.jpg", "Бірмінгем", ""),
                                        createCity("https://www.orangesmile.com/common/img_cities_original/brighton--2590884-0.jpg", "Брайтон", ""),
                                        createCity("https://mandry.club/wp-content/uploads/2019/07/manchester-velyka-brytaniya.jpg", "Манчестер", "")
                                )
                        )
                )
        );
        this.countryList.put(9L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/a/a4/Flag_of_the_United_States.svg", "США", "#eae7e2",
                        createCity(
                                Arrays.asList(
                                        createCity("https://www.forumdaily.com/wp-content/uploads/2017/05/Depositphotos_82409274_m-2015.jpg", "Нью-Йорк", ""),
                                        createCity("https://www.forumdaily.com/wp-content/uploads/2018/06/Depositphotos_31781801_m-2015-1.jpg", "Сан-Франциско", ""),
                                        createCity("https://sharespro.ru/sites/default/files/las-vegas.jpg", "Лас-Веґас", ""),
                                        createCity("https://triplook.me/media/resorts/photo/b/0/247.jpg", "Лос-Анджелес", "")
                                )
                        )
                )
        );
        this.countryList.put(10L,
                createCountry("https://upload.wikimedia.org/wikipedia/commons/c/ca/Flag_of_Iran.svg", "Іран", "#0fe40c",
                        createCity(
                                Arrays.asList(
                                        createCity("https://airlife.ua/wp-content/uploads/tegeran1.jpg", "Тегеран", ""),
                                        createCity("https://www.davidstar.net/afiles/upload/1561443427-5740.jpg", "Ісфаган", ""),
                                        createCity("https://ic.pics.livejournal.com/tatiana_gayduk/64770648/1519586/1519586_1000.jpg", "Шираз", ""),
                                        createCity("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Beautiful_Tabriz.JPG/1000px-Beautiful_Tabriz.JPG", "Тебріз", "")
                                )
                        )
                )
        );
    }

    public BigDecimal getMaxEcology() {
        return maxEcology;
    }

    public ConfigGame setMaxEcology(BigDecimal maxEcology) {
        this.maxEcology = maxEcology;
        return this;
    }

    public BigDecimal getMoneyStatic() {
        return moneyStatic;
    }

    public ConfigGame setMoneyStatic(BigDecimal moneyStatic) {
        this.moneyStatic = moneyStatic;
        return this;
    }

    public BigDecimal getPriceCreateShield() {
        return priceCreateShield;
    }

    public ConfigGame setPriceCreateShield(BigDecimal priceCreateShield) {
        this.priceCreateShield = priceCreateShield;
        return this;
    }

    public BigDecimal getDivideByOneHundred() {
        return divideByOneHundred;
    }

    public ConfigGame setDivideByOneHundred(BigDecimal divideByOneHundred) {
        this.divideByOneHundred = divideByOneHundred;
        return this;
    }

    public BigDecimal getDefaultGoldIncome() {
        return defaultGoldIncome;
    }

    public ConfigGame setDefaultGoldIncome(BigDecimal defaultGoldIncome) {
        this.defaultGoldIncome = defaultGoldIncome;
        return this;
    }

    public BigDecimal getDefaultCityUpgrade() {
        return defaultCityUpgrade;
    }

    public ConfigGame setDefaultCityUpgrade(BigDecimal defaultCityUpgrade) {
        this.defaultCityUpgrade = defaultCityUpgrade;
        return this;
    }

    public BigDecimal getGrowthCityUpgrade() {
        return growthCityUpgrade;
    }

    public ConfigGame setGrowthCityUpgrade(BigDecimal growthCityUpgrade) {
        this.growthCityUpgrade = growthCityUpgrade;
        return this;
    }

    public BigDecimal getPriceWeaponTechnology() {
        return priceWeaponTechnology;
    }

    public ConfigGame setPriceWeaponTechnology(BigDecimal priceWeaponTechnology) {
        this.priceWeaponTechnology = priceWeaponTechnology;
        return this;
    }

    public BigDecimal getPriceWeapon() {
        return priceWeapon;
    }

    public ConfigGame setPriceWeapon(BigDecimal priceWeapon) {
        this.priceWeapon = priceWeapon;
        return this;
    }

    public BigDecimal getPriceUpdateCity() {
        return priceUpdateCity;
    }

    public ConfigGame setPriceUpdateCity(BigDecimal priceUpdateCity) {
        this.priceUpdateCity = priceUpdateCity;
        return this;
    }

    public BigDecimal getPriceUpdateEcology() {
        return priceUpdateEcology;
    }

    public ConfigGame setPriceUpdateEcology(BigDecimal priceUpdateEcology) {
        this.priceUpdateEcology = priceUpdateEcology;
        return this;
    }

    public BigDecimal getNegativeEffectOfSanctions() {
        return negativeEffectOfSanctions;
    }

    public ConfigGame setNegativeEffectOfSanctions(BigDecimal negativeEffectOfSanctions) {
        this.negativeEffectOfSanctions = negativeEffectOfSanctions;
        return this;
    }

    public Map<Long, CountryList> getCountryList() {
        return countryList;
    }

    public ConfigGame setCountryList(Map<Long, CountryList> countryList) {
        this.countryList = countryList;
        return this;
    }

    public BigDecimal getMinValueEcology() {
        return minValueEcology;
    }

    public ConfigGame setMinValueEcology(BigDecimal minValueEcology) {
        this.minValueEcology = minValueEcology;
        return this;
    }

    public BigDecimal getGrowthOfEcology() {
        return growthOfEcology;
    }

    public ConfigGame setGrowthOfEcology(BigDecimal growthOfEcology) {
        this.growthOfEcology = growthOfEcology;
        return this;
    }

    public BigDecimal getFallingEcologyPerCreateWeapon() {
        return fallingEcologyPerCreateWeapon;
    }

    public ConfigGame setFallingEcologyPerCreateWeapon(BigDecimal fallingEcologyPerCreateWeapon) {
        this.fallingEcologyPerCreateWeapon = fallingEcologyPerCreateWeapon;
        return this;
    }

    public BigDecimal getFallingEcologyPerUsedWeapon() {
        return fallingEcologyPerUsedWeapon;
    }

    public ConfigGame setFallingEcologyPerUsedWeapon(BigDecimal fallingEcologyPerUsedWeapon) {
        this.fallingEcologyPerUsedWeapon = fallingEcologyPerUsedWeapon;
        return this;
    }

    public BigDecimal getBaseEcology() {
        return baseEcology;
    }

    public ConfigGame setBaseEcology(BigDecimal baseEcology) {
        this.baseEcology = baseEcology;
        return this;
    }

    private CountryList createCountry(String photo, String name, String color, Map<Long, CityList> cityList) {
        return new CountryList()
                .setPhoto(photo)
                .setName(name)
                .setColor(color)
                .setCityList(cityList);
    }

    private Map<Long, CityList> createCity(List<CityList> cityLists) {
        Map<Long, CityList> map = new HashMap();
        for (int i = 0; i < cityLists.size(); i++) {
            map.put((long) i, cityLists.get(i));
        }
        return map;
    }

    private CityList createCity(String photo, String name, String color) {
        return new CityList()
                .setPhoto(photo)
                .setName(name)
                .setColor(color);
    }
}
