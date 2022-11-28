package com.example.profiladaptatif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())

    val movie = MutableStateFlow(MovieDetail())

    val credits = MutableStateFlow(Credits())

    val series = MutableStateFlow<List<Serie>>(listOf())

    val creditSerie = MutableStateFlow(CreditsSerie())

    val serie = MutableStateFlow(Serie())

    val acteurs = MutableStateFlow<List<Result>>(listOf())

    val apikey = "d2ee8f9a0abe429c115a40452040c23a"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory((MoshiConverterFactory.create()))
        .build()
        .create(TmdbAPI::class.java)

    fun affichMovies(){
        viewModelScope.launch {
            movies.value = repo.lastMovies()
        }
    }

    fun detailMovie(id: String){
        viewModelScope.launch {
            movie.value = service.detailFilm(id, apikey)
        }
    }
    fun creditMovie(id: String){
        viewModelScope.launch {
            credits.value = service.creditFilm(id, apikey)
        }
    }

    fun affichSeries(){
        viewModelScope.launch {
            series.value = repo.lastSeries()
        }
    }

    fun detailSerie(id: String){
        viewModelScope.launch {
            serie.value = service.detailSerie(id, apikey)
        }
    }
    fun creditSerie(id: String){
        viewModelScope.launch {
            creditSerie.value = service.creditSerie(id, apikey)
        }
    }

    fun searchMovies(search:String){
        viewModelScope.launch {
            movies.value = service.searchFilm(apikey = apikey, searchtext = search).results
        }
    }

    fun affichActeurs(){
        viewModelScope.launch {
            acteurs.value = repo.lastActors()
        }
    }

    /*
    viewModelScope.launch {
            //movies.value = service.getFilmsRecents(apikey).results
            val json = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg\",\"genre_ids\":[27,53],\"id\":760161,\"original_language\":\"en\",\"original_title\":\"Orphan: First Kill\",\"overview\":\"Après s'être échappée d'un établissement psychiatrique estonien, Leena Klammer se rend en Amérique en se faisant passer pour Esther, la fille disparue d'une riche famille. Mais lorsque son masque commence à tomber, elle se retrouve face à une mère qui veut à tout prix protéger sa famille de l'\\\"enfant\\\" meurtrière.\",\"popularity\":4696.941,\"poster_path\":\"/7JDROXsXbp1MBrtG2GRAN7hmRfE.jpg\",\"release_date\":\"2022-07-27\",\"title\":\"Esther 2 : Les Origines\",\"video\":false,\"vote_average\":6.8,\"vote_count\":1013},{\"adult\":false,\"backdrop_path\":\"/5hoS3nEkGGXUfmnu39yw1k52JX5.jpg\",\"genre_ids\":[28,12,14],\"id\":960704,\"original_language\":\"ja\",\"original_title\":\"鋼の錬金術師 完結編 最後の錬成\",\"overview\":\"Le long et tortueux périple des frères Elric s'achève dans ce dénouement épique, où ils doivent combattre une menace qui se veut divine et pourrait détruire leur nation.\",\"popularity\":3686.296,\"poster_path\":\"/y6b8OraLybW0O4VFIxnmkPl9ZJc.jpg\",\"release_date\":\"2022-06-24\",\"title\":\"Fullmetal Alchemist : La dernière alchimie\",\"video\":false,\"vote_average\":6.4,\"vote_count\":79},{\"adult\":false,\"backdrop_path\":\"/iS9U3VHpPEjTWnwmW56CrBlpgLj.jpg\",\"genre_ids\":[14,35,10751],\"id\":642885,\"original_language\":\"en\",\"original_title\":\"Hocus Pocus 2\",\"overview\":\"Les horribles sœurs Sanderson sont de retour ! Dans cette nouvelle aventure, le trio infernal - tout droit surgi du XVIIe siècle et assoiffé de vengeance. va une fois de plus s’acharner sur la ville de Salem. Mais en cette veille de Toussaint, trois courageux lycéens vont tenter le tout pour le tout pour empêcher ces sorcières en furie de ravager la ville...\",\"popularity\":3670.384,\"poster_path\":\"/aQQmqCeAxAlecrqEU2YTCYsicao.jpg\",\"release_date\":\"2022-09-27\",\"title\":\"Hocus Pocus 2\",\"video\":false,\"vote_average\":7.7,\"vote_count\":738},{\"adult\":false,\"backdrop_path\":\"/etP5jwlwvkNhwe7jnI2AyA6ZKrR.jpg\",\"genre_ids\":[878],\"id\":575322,\"original_language\":\"en\",\"original_title\":\"Звёздный разум\",\"overview\":\"Dans un futur proche… Après des siècles d’errance environnementale, les jours de la Terre sont comptés. Avec elle, c’est l’espèce humaine qui est menacée d’extinction. Mais l’espoir renaît lorsqu’un jeune scientifique dévoile un procédé révolutionnaire de terraformation, qui ouvre la voie vers la colonisation de nouvelles planètes. Un groupe d’élite de tous bords et de toutes nationalités est constitué. Sa mission : prendre les commandes du fleuron de la flotte internationale, et partir en quête d’un nouveau foyer pour l’humanité. Naviguant vers l’inconnu, ces nouveaux explorateurs sont alors loin d’imaginer les immenses dangers qui les guettent.\",\"popularity\":3661.034,\"poster_path\":\"/ssbDfH9xN857NyfJDgR9kfKFMBe.jpg\",\"release_date\":\"2022-01-06\",\"title\":\"Project Gemini\",\"video\":false,\"vote_average\":5.4,\"vote_count\":89},{\"adult\":false,\"backdrop_path\":\"/83oeqwN64WtafGoITvsOzjKIQaM.jpg\",\"genre_ids\":[28,35,53],\"id\":718930,\"original_language\":\"en\",\"original_title\":\"Bullet Train\",\"overview\":\"Coccinelle est un assassin malchanceux et particulièrement déterminé à accomplir sa nouvelle mission paisiblement après que trop d'entre elles aient déraillé. Mais le destin en a décidé autrement et l'embarque dans le train le plus rapide au monde aux côtés d'adversaires redoutables qui ont tous un point commun, mais dont les intérêts divergent radicalement... Il doit alors tenter par tous les moyens de descendre du train.\",\"popularity\":3605.177,\"poster_path\":\"/uJvMXO1DNEER2lZocNjJy2zXtYs.jpg\",\"release_date\":\"2022-07-03\",\"title\":\"Bullet Train\",\"video\":false,\"vote_average\":7.5,\"vote_count\":1787},{\"adult\":false,\"backdrop_path\":\"/1DBDwevWS8OhiT3wqqlW7KGPd6m.jpg\",\"genre_ids\":[53],\"id\":985939,\"original_language\":\"en\",\"original_title\":\"Fall\",\"overview\":\"Pour les meilleurs amis Becky et Hunter, la vie consiste à vaincre ses peurs et à repousser ses limites. Mais après avoir grimpé à 2 000 pieds au sommet d'une tour de radio isolée et abandonnée, elles se retrouvent bloquées sans aucun moyen de descendre. Maintenant, les compétences expertes en escalade de Becky et Hunter seront mises à l'épreuve ultime alors qu'elle se battent désespérément pour survivre aux éléments, au manque de fournitures et aux hauteurs vertigineuses.\",\"popularity\":3540.089,\"poster_path\":\"/spCAxD99U1A6jsiePFoqdEcY0dG.jpg\",\"release_date\":\"2022-08-11\",\"title\":\"Fall\",\"video\":false,\"vote_average\":7.4,\"vote_count\":1437},{\"adult\":false,\"backdrop_path\":\"/ghsPsvM0sEztdNT4kUlTsBF2LEF.jpg\",\"genre_ids\":[18,28,53],\"id\":852046,\"original_language\":\"fr\",\"original_title\":\"Athena\",\"overview\":\"La mort tragique de leur frère cadet dans des circonstances troubles, va en quelques heures faire basculer la vie d'une fratrie dans le chaos.\",\"popularity\":3115.78,\"poster_path\":\"/66hefmZ1ZVLO1Hb57sZVGSlDAmM.jpg\",\"release_date\":\"2022-09-09\",\"title\":\"Athena\",\"video\":false,\"vote_average\":6.6,\"vote_count\":270},{\"adult\":false,\"backdrop_path\":\"/aIkG2V4UXrfkxMdJZmq30xO0QQr.jpg\",\"genre_ids\":[878,12,28],\"id\":791155,\"original_language\":\"en\",\"original_title\":\"Secret Headquarters\",\"overview\":\"Un enfant découvre le quartier général secret du super-héros le plus puissant du monde caché sous sa maison et doit le défendre avec son groupe d'amis lorsque les méchants attaquent.\",\"popularity\":2672.663,\"poster_path\":\"/8PsHogUfvjWPGdWAI5uslDhHDx7.jpg\",\"release_date\":\"2022-08-12\",\"title\":\"Secret Headquarters\",\"video\":false,\"vote_average\":6.9,\"vote_count\":97},{\"adult\":false,\"backdrop_path\":\"/7AiIrnDMaOhPrw9elJ5NNjijTW4.jpg\",\"genre_ids\":[53,9648],\"id\":916605,\"original_language\":\"en\",\"original_title\":\"The Infernal Machine\",\"overview\":\"Bruce Cogburn, un auteur reclus et controversé, est extirpé de sa cachette quand il reçoit des lettres interminables d'un fan obsessionnel. Il est alors plongé dans un labyrinthe dangereux en cherchant l'auteur de ces messages énigmatiques.\",\"popularity\":2709.944,\"poster_path\":\"/bSqpOGzaKBdGkBLmcm1JJIVryYy.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"The Infernal Machine\",\"video\":false,\"vote_average\":7,\"vote_count\":74},{\"adult\":false,\"backdrop_path\":\"/sobIeWp1a3saZTBkoRTAf8sfC7J.jpg\",\"genre_ids\":[12,10751,10770],\"id\":335795,\"original_language\":\"en\",\"original_title\":\"Monster High: The Movie\",\"overview\":\"Une adaptation de la célèbre ligne de jouets Mattel qui évoque les années lycée de poupées monstrueuses et effroyablement mode à l'école de Monster High.\",\"popularity\":2105.212,\"poster_path\":\"/tn3GWm0Erehkpur8PUuYWxGpul5.jpg\",\"release_date\":\"2022-10-06\",\"title\":\"Monster High: The Movie\",\"video\":false,\"vote_average\":7,\"vote_count\":79},{\"adult\":false,\"backdrop_path\":\"/nnUQqlVZeEGuCRx8SaoCU4XVHJN.jpg\",\"genre_ids\":[14,12,10751],\"id\":532639,\"original_language\":\"en\",\"original_title\":\"Pinocchio\",\"overview\":\"La célèbre histoire de ce pantin de bois, Pinocchio, bien décidé à vivre la plus palpitante des aventures pour devenir un vrai petit garçon.\",\"popularity\":2251.388,\"poster_path\":\"/nch8NTH45TBH4JyPuugttPzoxau.jpg\",\"release_date\":\"2022-09-07\",\"title\":\"Pinocchio\",\"video\":false,\"vote_average\":6.7,\"vote_count\":917},{\"adult\":false,\"backdrop_path\":\"/aTovumsNlDjof7YVoU5nW2RHaYn.jpg\",\"genre_ids\":[27,53],\"id\":616820,\"original_language\":\"en\",\"original_title\":\"Halloween Ends\",\"overview\":\"Quatre ans après les événements d’Halloween Kills, Laurie vit désormais avec sa petite-fille Allyson et achève d’écrire ses mémoires. Michael Myers ne s’est pas manifesté ces derniers temps. Après avoir laissé l’ombre de Michael planer sur le cours de son existence pendant des décennies, elle a enfin décidé de s’affranchir de la peur et de la colère et de se tourner vers la vie. Mais lorsqu’un jeune homme, Corey Cunningham, est accusé d’avoir assassiné un garçon qu’il gardait, Laurie devra affronter une dernière fois les forces maléfiques qui lui échappent, dans un déferlement de violence et de terreur…\",\"popularity\":1983.169,\"poster_path\":\"/fvBIKpbLN3eowIJgsligbN3S0LR.jpg\",\"release_date\":\"2022-10-12\",\"title\":\"Halloween Ends\",\"video\":false,\"vote_average\":7.1,\"vote_count\":270},{\"adult\":false,\"backdrop_path\":\"/pfAZP7JvTTxqgq7n6A1OYgkAdEW.jpg\",\"genre_ids\":[28,14,27,10770],\"id\":894205,\"original_language\":\"en\",\"original_title\":\"Werewolf by Night\",\"overview\":\"Par une nuit sombre, une cabale secrète de chasseurs de monstres sort de l'ombre et se réunit dans un sinistre temple après la mort de leur chef. Dans un mémorial étrange et macabre, les participants sont poussés dans une compétition mystérieuse et mortelle pour une relique puissante - une chasse qui les amènera finalement à faire face à un monstre dangereux.\",\"popularity\":1891.281,\"poster_path\":\"/5p6Q5dsqgT7dknImjtoRvNx50k9.jpg\",\"release_date\":\"2022-09-25\",\"title\":\"Werewolf by Night\",\"video\":false,\"vote_average\":7.3,\"vote_count\":416},{\"adult\":false,\"backdrop_path\":\"/rgZ3hdzgMgYgzvBfwNEVW01bpK1.jpg\",\"genre_ids\":[28,53,18],\"id\":429473,\"original_language\":\"en\",\"original_title\":\"Lou\",\"overview\":\"Une jeune fille est enlevée à la faveur d'un orage gigantesque. Sa mère s'allie à sa mystérieuse voisine pour se lancer à la poursuite du kidnappeur. Leur périple va mettre leurs limites à l'épreuve et exposer les sombres secrets de leur passé.\",\"popularity\":2131.446,\"poster_path\":\"/djM2s4wSaATn4jVB33cV05PEbV7.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"Lou\",\"video\":false,\"vote_average\":6.5,\"vote_count\":272},{\"adult\":false,\"backdrop_path\":\"/olPXihyFeeNvnaD6IOBltgIV1FU.jpg\",\"genre_ids\":[27,9648],\"id\":882598,\"original_language\":\"en\",\"original_title\":\"Smile\",\"overview\":\"Après avoir été témoin d'un incident traumatisant impliquant l’une de ses patientes, la vie de la psychiatre Rose Cotter (Sosie Bacon) tourne au cauchemar. Terrassée par une force mystérieuse, Rose va devoir se confronter à son passé pour tenter de survivre …\",\"popularity\":1704.232,\"poster_path\":\"/zySvx4uY3PhdlDn6FsRXgfWvR3C.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"Smile\",\"video\":false,\"vote_average\":6.8,\"vote_count\":223},{\"adult\":false,\"backdrop_path\":\"/2k9tBql5GYH328Krj66tDT9LtFZ.jpg\",\"genre_ids\":[53,12,27],\"id\":760741,\"original_language\":\"en\",\"original_title\":\"Beast\",\"overview\":\"Le Dr. Nate Daniels, revient en Afrique du Sud, où il a autrefois rencontré sa femme aujourd’hui décédée, pour y passer des vacances prévues de longue date avec ses deux filles dans une réserve naturelle, tenue par Martin Battles, un vieil ami de la famille, biologiste spécialiste de la vie sauvage. Mais ce repos salvateur va se transformer en épreuve de survie quand un lion assoiffé de vengeance, unique rescapé de la traque sanguinaire d’ignobles braconniers, se met à dévorer tout humain sur sa route et prend en chasse le docteur et sa famille.\",\"popularity\":1882.318,\"poster_path\":\"/kmWpbWYu4gT7wIV4amP0gMDjNHj.jpg\",\"release_date\":\"2022-08-11\",\"title\":\"Beast\",\"video\":false,\"vote_average\":7,\"vote_count\":662},{\"adult\":false,\"backdrop_path\":\"/92PJmMopfy64VYjd0HvIQaHGZX0.jpg\",\"genre_ids\":[10751,35,16,28],\"id\":366672,\"original_language\":\"en\",\"original_title\":\"Paws of Fury: The Legend of Hank\",\"overview\":\"Hank est un chien enjoué qui rêve d’être samouraï dans un monde où ce privilège n’est réservé… qu’aux chats !  Moqué, refusé par toutes les écoles de samouraïs, il rencontre un gros matou grincheux, un maître guerrier qui finit par accepter de lui enseigner les techniques ancestrales des samouraïs.  L’apprentissage va être rude pour le jeune chien remuant et dissipé : il faut apprendre à manier le sabre, devenir agile comme un chat, maîtriser les arts martiaux, et Hank n’est pas très doué. Mais pour devenir samouraï, Hank se donne… un mal de chien !  Quand l’armée de chats du Shogun déferle sur la ville, le courage et l’astuce de l’apprenti samouraï vont enfin s’avérer utiles : « chat va barder, il va leur mettre la pâtée » !\",\"popularity\":1711.037,\"poster_path\":\"/6yD3h1jrTbnPvToXhGfOGFw53PV.jpg\",\"release_date\":\"2022-07-14\",\"title\":\"Samouraï Academy\",\"video\":false,\"vote_average\":6.8,\"vote_count\":144},{\"adult\":false,\"backdrop_path\":\"/rwgmDkIEv8VjAsWx25ottJrFvpO.jpg\",\"genre_ids\":[10749,18],\"id\":744276,\"original_language\":\"en\",\"original_title\":\"After Ever Happy\",\"overview\":\"Le quatrième et dernier volet des aventures de Tessa et Hardin.  Une révélation sur le passé de Tessa ébranle le couple qu'elle forme avec Hardin. Parallèlement, une vérité choquante sur leur famille respective surgit. Les deux amants ne sont pas si différents l'un de l'autre. Tessa n'est plus la gentille fille qu'elle était lorsqu'elle a rencontré Hardin, lui, n'est plus que le garçon lunatique dont elle est tombée amoureuse.\",\"popularity\":1567.679,\"poster_path\":\"/zqd0c9uJQ5mjJvieiRN4VkpJzTs.jpg\",\"release_date\":\"2022-08-24\",\"title\":\"After : Chapitre 4\",\"video\":false,\"vote_average\":7,\"vote_count\":410},{\"adult\":false,\"backdrop_path\":\"/jsoz1HlxczSuTx0mDl2h0lxy36l.jpg\",\"genre_ids\":[14,28,35],\"id\":616037,\"original_language\":\"en\",\"original_title\":\"Thor: Love and Thunder\",\"overview\":\"Alors que Thor est en pleine introspection et en quête de sérénité, sa retraite est interrompue par un tueur galactique connu sous le nom de Gorr, qui s’est donné pour mission d’exterminer tous les dieux. Pour affronter cette menace, Thor demande l’aide de Valkyrie, de Korg et de son ex-petite amie Jane Foster, qui, à sa grande surprise, manie inexplicablement son puissant marteau, le Mjolnir. Ensemble, ils se lancent dans une dangereuse aventure cosmique pour comprendre les motivations qui poussent Gorr à la vengeance et l’arrêter avant qu’il ne soit trop tard.\",\"popularity\":1769.509,\"poster_path\":\"/kSMarEm3ESOOr11dzsep2RZ1ClD.jpg\",\"release_date\":\"2022-07-06\",\"title\":\"Thor : Love and Thunder\",\"video\":false,\"vote_average\":6.7,\"vote_count\":4220},{\"adult\":false,\"backdrop_path\":\"/qaTzVAW1u16WFNsepjCrilBuInc.jpg\",\"genre_ids\":[16,28,10751,35,878],\"id\":539681,\"original_language\":\"en\",\"original_title\":\"DC League of Super-Pets\",\"overview\":\"Krypto, le super-chien de Superman, se trouve face à un défi immense : sauver son maître, enlevé par Lex Luthor et son maléfique cochon d’inde Lulu. Pour cela, il devra faire équipe avec une bande d’animaux au grand cœur mais plutôt maladroits.\",\"popularity\":1579.876,\"poster_path\":\"/9Nf7UH8uExdV2Ta4UupmnYjCkYc.jpg\",\"release_date\":\"2022-07-27\",\"title\":\"Krypto et les Super-Animaux\",\"video\":false,\"vote_average\":7.5,\"vote_count\":809}],\"total_pages\":35471,\"total_results\":709420}"

            val moshi : Moshi = Moshi.Builder().build()
            val adapter : JsonAdapter<TmdbResult> = moshi.adapter(TmdbResult::class. java )
            val movie = adapter.fromJson(json)

            if(movie != null ) {
                Log.v("coucou", movie.results.toString())
                movies.value  = movie.results
            }

        }
     */
}