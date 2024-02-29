package net.harutiro.nationalweather.features.Weather.entities

enum class CityId(val id: String) {
    hokkaido("016010"),
    aomori("020010"),
    iwate("030010"),
    miyagi("040010"),
    akita("050010"),
    yamagata("060010"),
    fukushima("070010"),
    ibaraki("080010"),
    tochigi("090010"),
    gunma("100010"),
    saitama("110010"),
    chiba("120010"),
    tokyo("130010"),
    kanagawa("140010"),
    niigata("150010"),
    toyama("160010"),
    ishikawa("170010"),
    fukui("180010"),
    yamanashi("190010"),
    nagano("200010"),
    gifu("210010"),
    shizuoka("220010"),
    aichi("230010"),
    mie("240010"),
    shiga("250010"),
    kyoto("260010"),
    osaka("270000"),
    hyogo("280010"),
    nara("290010"),
    wakayama("300010"),
    tottori("310010"),
    shimane("320010"),
    okayama("330010"),
    hiroshima("340010"),
    yamaguchi("350010"),
    tokushima("360010"),
    kagawa("370000"),
    ehime("380010"),
    kochi("390010"),
    fukuoka("400010"),
    saga("410010"),
    nagasaki("420010"),
    kumamoto("430010"),
    oita("440010"),
    miyazaki("450010"),
    kagoshima("460010"),
    okinawa("471010");

    companion object {
        fun idToCityId(id: String): CityId? {
            return CityId.values().find { it.id == id }
        }
    }
}