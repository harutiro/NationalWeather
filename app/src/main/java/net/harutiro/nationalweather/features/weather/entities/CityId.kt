package net.harutiro.nationalweather.features.weather.entities

enum class CityId(val id: String) {
    HOKKAIDO("016010"),
    AOMORI("020010"),
    IWATE("030010"),
    MIYAGI("040010"),
    AKITA("050010"),
    YAMAGATA("060010"),
    FUKUSHIMA("070010"),
    IBARAKI("080010"),
    TOCHIGI("090010"),
    GUNMA("100010"),
    SAITAMA("110010"),
    CHIBA("120010"),
    TOKYO("130010"),
    KANAGAWA("140010"),
    NIIGATA("150010"),
    TOYAMA("160010"),
    ISHIKAWA("170010"),
    FUKUI("180010"),
    YAMANASHI("190010"),
    NAGANO("200010"),
    GIFU("210010"),
    SHIZUOKA("220010"),
    AICHI("230010"),
    MIE("240010"),
    SHIGA("250010"),
    KYOTO("260010"),
    OSAKA("270000"),
    HYOGO("280010"),
    NARA("290010"),
    WAKAYAMA("300010"),
    TOTTORI("310010"),
    SHIMANE("320010"),
    OKAYAMA("330010"),
    HIROSHIMA("340010"),
    YAMAGUCHI("350010"),
    TOKUSHIMA("360010"),
    KAGAWA("370000"),
    EHIME("380010"),
    KOCHI("390010"),
    FUKUOKA("400010"),
    SAGA("410010"),
    NAGASAKI("420010"),
    KUMAMOTO("430010"),
    OITA("440010"),
    MIYAZAKI("450010"),
    KAGOSHIMA("460010"),
    OKINAWA("471010"),
    ;

    companion object {
        fun idToCityId(id: String): CityId? {
            return entries.find { it.id == id }
        }
    }
}
