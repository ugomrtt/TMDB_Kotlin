package com.example.profiladaptatif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.ActeurEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.FilmEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.SerieEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Repo.Repository
import com.example.profiladaptatif.com.example.profiladaptatif.Repo.TmdbAPI
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
    val favMovies = MutableStateFlow<List<FilmEntity>>(listOf())


    val series = MutableStateFlow<List<Serie>>(listOf())
    val serie = MutableStateFlow(Serie())
    val creditSerie = MutableStateFlow(CreditsSerie())
    val favSeries = MutableStateFlow<List<SerieEntity>>(listOf())

    val acteurs = MutableStateFlow<List<Result>>(listOf())
    val favActeurs = MutableStateFlow<List<ActeurEntity>>(listOf())



    val apikey = "d2ee8f9a0abe429c115a40452040c23a"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory((MoshiConverterFactory.create()))
        .build()
        .create(TmdbAPI::class.java)

    fun affichMovies(){
        viewModelScope.launch {
            movies.value = repo.lastMovies()
            affichFavFilm()
        }
    }

    fun detailMovie(id: String){
        viewModelScope.launch {
            movie.value = repo.detailFilm(id)
        }
    }
    fun creditMovie(id: String){
        viewModelScope.launch {
            credits.value = repo.creditFilm(id)
        }
    }

    fun affichSeries(){
        viewModelScope.launch {
            series.value = repo.lastSeries()
        }
    }

    fun detailSerie(id: String){
        viewModelScope.launch {
            serie.value = repo.detailSerie(id)
        }
    }
    fun creditSerie(id: String){
        viewModelScope.launch {
            creditSerie.value = repo.creditSerie(id)
        }
    }

    fun searchMovies(search:String){
        viewModelScope.launch {
            movies.value = repo.searchFilm(search)
        }
    }

    fun searchSeries(search:String){
        viewModelScope.launch {
            series.value = repo.searchSeries(search)
        }
    }

    fun affichActeurs(){
        viewModelScope.launch {
            acteurs.value = repo.lastActors()
        }
    }

    fun affichFavFilm(){
        viewModelScope.launch {
            favMovies.value = repo.getFavFilms()
        }
    }

    fun affichFavSeries(){
        viewModelScope.launch {
            favSeries.value = repo.getFavSeries()
        }
    }

    fun affichFavActeurs(){
        viewModelScope.launch {
            favActeurs.value = repo.gatFavActeurs()
        }
    }

    fun addFavMovie(movie: Movie){
        viewModelScope.launch {
            repo.isFavFilm(movie);
            val favMovies = repo.getFavFilms()
            val newListMovies = mutableListOf<Movie>()
            movies.value.map { movie ->
                if(favMovies.any{isFavMovie ->
                        isFavMovie.id == movie.id.toString();
                    }){
                    val newFavMovie = movie.copy(isFav = true)
                    newListMovies.add(newFavMovie)
                }else{
                    newListMovies.add(movie)
                }
            }
            movies.value = newListMovies
            affichFavFilm()
        }
    }

    fun deleteFavMovie(movie: Movie){
        viewModelScope.launch {
            repo.notFavFilm(movie.id);
            val favMovies = repo.getFavFilms()
            val newListMovies = mutableListOf<Movie>()
            movies.value.map { movie ->
                if(favMovies.any{isFav ->
                        isFav.id == movie.id.toString()
                    }){
                    newListMovies.add(movie.copy(isFav = true))
                }else{
                    newListMovies.add(movie.copy(isFav = false))
                }
            }
            movies.value = newListMovies
            affichFavFilm()
        }
    }


    fun addFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.isFavSerie(serie);
            val favSeries = repo.getFavSeries()
            val newListSeries = mutableListOf<Serie>()
            series.value.map { serie ->
                if(favSeries.any{isFavSerie ->
                        isFavSerie.id == serie.id.toString();
                    }){
                    val newFavSerie = serie.copy(isFav = true)
                    newListSeries.add(newFavSerie)
                }else{
                    newListSeries.add(serie)
                }
            }
            series.value = newListSeries
            affichFavSeries()
        }
    }

    fun deleteFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.notFavSerie(serie.id);
            val favSeries = repo.getFavSeries()

            val newListSeries = mutableListOf<Serie>()

            series.value.map { serie ->
                if(favSeries.any{isFav ->
                        isFav.id == serie.id.toString()
                    }){
                    newListSeries.add(serie.copy(isFav = true))
                }else{
                    newListSeries.add(serie.copy(isFav = false))
                }
            }
            series.value = newListSeries
            affichFavSeries()
        }
    }

    fun addFavActeur(acteur: Result){
        viewModelScope.launch {
            repo.isFavActeur(acteur);
            val favActeurs = repo.gatFavActeurs()
            val newListActeurs = mutableListOf<Result>()
            acteurs.value.map { acteur ->
                if(favActeurs.any{isFavActeur ->
                        isFavActeur.id == acteur.id.toString();
                    }){
                    val newFavActeur = acteur.copy(isFav = true)
                    newListActeurs.add(newFavActeur)
                }else{
                    newListActeurs.add(acteur)
                }
            }
            acteurs.value = newListActeurs
            affichFavActeurs()
        }
    }

    fun deleteFavActeur(acteur: Result){
        viewModelScope.launch {
            repo.notFavActeur(acteur.id);
            val favActeurs = repo.gatFavActeurs()

            val newListActeurs = mutableListOf<Result>()

            acteurs.value.map { acteur ->
                if(favActeurs.any{isFav ->
                        isFav.id == acteur.id.toString()
                    }){
                    newListActeurs.add(acteur.copy(isFav = true))
                }else{
                    newListActeurs.add(acteur.copy(isFav = false))
                }
            }
            acteurs.value = newListActeurs
            affichFavActeurs()
        }
    }



    /*
    viewModelScope.launch {
            //movies.value = service.getFilmsRecents(apikey).results
            val json = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg\",\"genre_ids\":[27,53],\"id\":760161,\"original_language\":\"en\",\"original_title\":\"Orphan: First Kill\",\"overview\":\"Apr??s s'??tre ??chapp??e d'un ??tablissement psychiatrique estonien, Leena Klammer se rend en Am??rique en se faisant passer pour Esther, la fille disparue d'une riche famille. Mais lorsque son masque commence ?? tomber, elle se retrouve face ?? une m??re qui veut ?? tout prix prot??ger sa famille de l'\\\"enfant\\\" meurtri??re.\",\"popularity\":4696.941,\"poster_path\":\"/7JDROXsXbp1MBrtG2GRAN7hmRfE.jpg\",\"release_date\":\"2022-07-27\",\"title\":\"Esther 2 : Les Origines\",\"video\":false,\"vote_average\":6.8,\"vote_count\":1013},{\"adult\":false,\"backdrop_path\":\"/5hoS3nEkGGXUfmnu39yw1k52JX5.jpg\",\"genre_ids\":[28,12,14],\"id\":960704,\"original_language\":\"ja\",\"original_title\":\"?????????????????? ????????? ???????????????\",\"overview\":\"Le long et tortueux p??riple des fr??res Elric s'ach??ve dans ce d??nouement ??pique, o?? ils doivent combattre une menace qui se veut divine et pourrait d??truire leur nation.\",\"popularity\":3686.296,\"poster_path\":\"/y6b8OraLybW0O4VFIxnmkPl9ZJc.jpg\",\"release_date\":\"2022-06-24\",\"title\":\"Fullmetal Alchemist : La derni??re alchimie\",\"video\":false,\"vote_average\":6.4,\"vote_count\":79},{\"adult\":false,\"backdrop_path\":\"/iS9U3VHpPEjTWnwmW56CrBlpgLj.jpg\",\"genre_ids\":[14,35,10751],\"id\":642885,\"original_language\":\"en\",\"original_title\":\"Hocus Pocus 2\",\"overview\":\"Les horribles s??urs Sanderson sont de retour ! Dans cette nouvelle aventure, le trio infernal - tout droit surgi du XVIIe si??cle et assoiff?? de vengeance. va une fois de plus s???acharner sur la ville de Salem. Mais en cette veille de Toussaint, trois courageux lyc??ens vont tenter le tout pour le tout pour emp??cher ces sorci??res en furie de ravager la ville...\",\"popularity\":3670.384,\"poster_path\":\"/aQQmqCeAxAlecrqEU2YTCYsicao.jpg\",\"release_date\":\"2022-09-27\",\"title\":\"Hocus Pocus 2\",\"video\":false,\"vote_average\":7.7,\"vote_count\":738},{\"adult\":false,\"backdrop_path\":\"/etP5jwlwvkNhwe7jnI2AyA6ZKrR.jpg\",\"genre_ids\":[878],\"id\":575322,\"original_language\":\"en\",\"original_title\":\"???????????????? ??????????\",\"overview\":\"Dans un futur proche??? Apr??s des si??cles d???errance environnementale, les jours de la Terre sont compt??s. Avec elle, c???est l???esp??ce humaine qui est menac??e d???extinction. Mais l???espoir rena??t lorsqu???un jeune scientifique d??voile un proc??d?? r??volutionnaire de terraformation, qui ouvre la voie vers la colonisation de nouvelles plan??tes. Un groupe d?????lite de tous bords et de toutes nationalit??s est constitu??. Sa mission : prendre les commandes du fleuron de la flotte internationale, et partir en qu??te d???un nouveau foyer pour l???humanit??. Naviguant vers l???inconnu, ces nouveaux explorateurs sont alors loin d???imaginer les immenses dangers qui les guettent.\",\"popularity\":3661.034,\"poster_path\":\"/ssbDfH9xN857NyfJDgR9kfKFMBe.jpg\",\"release_date\":\"2022-01-06\",\"title\":\"Project Gemini\",\"video\":false,\"vote_average\":5.4,\"vote_count\":89},{\"adult\":false,\"backdrop_path\":\"/83oeqwN64WtafGoITvsOzjKIQaM.jpg\",\"genre_ids\":[28,35,53],\"id\":718930,\"original_language\":\"en\",\"original_title\":\"Bullet Train\",\"overview\":\"Coccinelle est un assassin malchanceux et particuli??rement d??termin?? ?? accomplir sa nouvelle mission paisiblement apr??s que trop d'entre elles aient d??raill??. Mais le destin en a d??cid?? autrement et l'embarque dans le train le plus rapide au monde aux c??t??s d'adversaires redoutables qui ont tous un point commun, mais dont les int??r??ts divergent radicalement... Il doit alors tenter par tous les moyens de descendre du train.\",\"popularity\":3605.177,\"poster_path\":\"/uJvMXO1DNEER2lZocNjJy2zXtYs.jpg\",\"release_date\":\"2022-07-03\",\"title\":\"Bullet Train\",\"video\":false,\"vote_average\":7.5,\"vote_count\":1787},{\"adult\":false,\"backdrop_path\":\"/1DBDwevWS8OhiT3wqqlW7KGPd6m.jpg\",\"genre_ids\":[53],\"id\":985939,\"original_language\":\"en\",\"original_title\":\"Fall\",\"overview\":\"Pour les meilleurs amis Becky et Hunter, la vie consiste ?? vaincre ses peurs et ?? repousser ses limites. Mais apr??s avoir grimp?? ?? 2 000 pieds au sommet d'une tour de radio isol??e et abandonn??e, elles se retrouvent bloqu??es sans aucun moyen de descendre. Maintenant, les comp??tences expertes en escalade de Becky et Hunter seront mises ?? l'??preuve ultime alors qu'elle se battent d??sesp??r??ment pour survivre aux ??l??ments, au manque de fournitures et aux hauteurs vertigineuses.\",\"popularity\":3540.089,\"poster_path\":\"/spCAxD99U1A6jsiePFoqdEcY0dG.jpg\",\"release_date\":\"2022-08-11\",\"title\":\"Fall\",\"video\":false,\"vote_average\":7.4,\"vote_count\":1437},{\"adult\":false,\"backdrop_path\":\"/ghsPsvM0sEztdNT4kUlTsBF2LEF.jpg\",\"genre_ids\":[18,28,53],\"id\":852046,\"original_language\":\"fr\",\"original_title\":\"Athena\",\"overview\":\"La mort tragique de leur fr??re cadet dans des circonstances troubles, va en quelques heures faire basculer la vie d'une fratrie dans le chaos.\",\"popularity\":3115.78,\"poster_path\":\"/66hefmZ1ZVLO1Hb57sZVGSlDAmM.jpg\",\"release_date\":\"2022-09-09\",\"title\":\"Athena\",\"video\":false,\"vote_average\":6.6,\"vote_count\":270},{\"adult\":false,\"backdrop_path\":\"/aIkG2V4UXrfkxMdJZmq30xO0QQr.jpg\",\"genre_ids\":[878,12,28],\"id\":791155,\"original_language\":\"en\",\"original_title\":\"Secret Headquarters\",\"overview\":\"Un enfant d??couvre le quartier g??n??ral secret du super-h??ros le plus puissant du monde cach?? sous sa maison et doit le d??fendre avec son groupe d'amis lorsque les m??chants attaquent.\",\"popularity\":2672.663,\"poster_path\":\"/8PsHogUfvjWPGdWAI5uslDhHDx7.jpg\",\"release_date\":\"2022-08-12\",\"title\":\"Secret Headquarters\",\"video\":false,\"vote_average\":6.9,\"vote_count\":97},{\"adult\":false,\"backdrop_path\":\"/7AiIrnDMaOhPrw9elJ5NNjijTW4.jpg\",\"genre_ids\":[53,9648],\"id\":916605,\"original_language\":\"en\",\"original_title\":\"The Infernal Machine\",\"overview\":\"Bruce Cogburn, un auteur reclus et controvers??, est extirp?? de sa cachette quand il re??oit des lettres interminables d'un fan obsessionnel. Il est alors plong?? dans un labyrinthe dangereux en cherchant l'auteur de ces messages ??nigmatiques.\",\"popularity\":2709.944,\"poster_path\":\"/bSqpOGzaKBdGkBLmcm1JJIVryYy.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"The Infernal Machine\",\"video\":false,\"vote_average\":7,\"vote_count\":74},{\"adult\":false,\"backdrop_path\":\"/sobIeWp1a3saZTBkoRTAf8sfC7J.jpg\",\"genre_ids\":[12,10751,10770],\"id\":335795,\"original_language\":\"en\",\"original_title\":\"Monster High: The Movie\",\"overview\":\"Une adaptation de la c??l??bre ligne de jouets Mattel qui ??voque les ann??es lyc??e de poup??es monstrueuses et effroyablement mode ?? l'??cole de Monster High.\",\"popularity\":2105.212,\"poster_path\":\"/tn3GWm0Erehkpur8PUuYWxGpul5.jpg\",\"release_date\":\"2022-10-06\",\"title\":\"Monster High: The Movie\",\"video\":false,\"vote_average\":7,\"vote_count\":79},{\"adult\":false,\"backdrop_path\":\"/nnUQqlVZeEGuCRx8SaoCU4XVHJN.jpg\",\"genre_ids\":[14,12,10751],\"id\":532639,\"original_language\":\"en\",\"original_title\":\"Pinocchio\",\"overview\":\"La c??l??bre histoire de ce pantin de bois, Pinocchio, bien d??cid?? ?? vivre la plus palpitante des aventures pour devenir un vrai petit gar??on.\",\"popularity\":2251.388,\"poster_path\":\"/nch8NTH45TBH4JyPuugttPzoxau.jpg\",\"release_date\":\"2022-09-07\",\"title\":\"Pinocchio\",\"video\":false,\"vote_average\":6.7,\"vote_count\":917},{\"adult\":false,\"backdrop_path\":\"/aTovumsNlDjof7YVoU5nW2RHaYn.jpg\",\"genre_ids\":[27,53],\"id\":616820,\"original_language\":\"en\",\"original_title\":\"Halloween Ends\",\"overview\":\"Quatre ans apr??s les ??v??nements d???Halloween Kills, Laurie vit d??sormais avec sa petite-fille Allyson et ach??ve d?????crire ses m??moires. Michael Myers ne s???est pas manifest?? ces derniers temps. Apr??s avoir laiss?? l???ombre de Michael planer sur le cours de son existence pendant des d??cennies, elle a enfin d??cid?? de s???affranchir de la peur et de la col??re et de se tourner vers la vie. Mais lorsqu???un jeune homme, Corey Cunningham, est accus?? d???avoir assassin?? un gar??on qu???il gardait, Laurie devra affronter une derni??re fois les forces mal??fiques qui lui ??chappent, dans un d??ferlement de violence et de terreur???\",\"popularity\":1983.169,\"poster_path\":\"/fvBIKpbLN3eowIJgsligbN3S0LR.jpg\",\"release_date\":\"2022-10-12\",\"title\":\"Halloween Ends\",\"video\":false,\"vote_average\":7.1,\"vote_count\":270},{\"adult\":false,\"backdrop_path\":\"/pfAZP7JvTTxqgq7n6A1OYgkAdEW.jpg\",\"genre_ids\":[28,14,27,10770],\"id\":894205,\"original_language\":\"en\",\"original_title\":\"Werewolf by Night\",\"overview\":\"Par une nuit sombre, une cabale secr??te de chasseurs de monstres sort de l'ombre et se r??unit dans un sinistre temple apr??s la mort de leur chef. Dans un m??morial ??trange et macabre, les participants sont pouss??s dans une comp??tition myst??rieuse et mortelle pour une relique puissante - une chasse qui les am??nera finalement ?? faire face ?? un monstre dangereux.\",\"popularity\":1891.281,\"poster_path\":\"/5p6Q5dsqgT7dknImjtoRvNx50k9.jpg\",\"release_date\":\"2022-09-25\",\"title\":\"Werewolf by Night\",\"video\":false,\"vote_average\":7.3,\"vote_count\":416},{\"adult\":false,\"backdrop_path\":\"/rgZ3hdzgMgYgzvBfwNEVW01bpK1.jpg\",\"genre_ids\":[28,53,18],\"id\":429473,\"original_language\":\"en\",\"original_title\":\"Lou\",\"overview\":\"Une jeune fille est enlev??e ?? la faveur d'un orage gigantesque. Sa m??re s'allie ?? sa myst??rieuse voisine pour se lancer ?? la poursuite du kidnappeur. Leur p??riple va mettre leurs limites ?? l'??preuve et exposer les sombres secrets de leur pass??.\",\"popularity\":2131.446,\"poster_path\":\"/djM2s4wSaATn4jVB33cV05PEbV7.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"Lou\",\"video\":false,\"vote_average\":6.5,\"vote_count\":272},{\"adult\":false,\"backdrop_path\":\"/olPXihyFeeNvnaD6IOBltgIV1FU.jpg\",\"genre_ids\":[27,9648],\"id\":882598,\"original_language\":\"en\",\"original_title\":\"Smile\",\"overview\":\"Apr??s avoir ??t?? t??moin d'un incident traumatisant impliquant l???une de ses patientes, la vie de la psychiatre Rose Cotter (Sosie Bacon) tourne au cauchemar. Terrass??e par une force myst??rieuse, Rose va devoir se confronter ?? son pass?? pour tenter de survivre ???\",\"popularity\":1704.232,\"poster_path\":\"/zySvx4uY3PhdlDn6FsRXgfWvR3C.jpg\",\"release_date\":\"2022-09-23\",\"title\":\"Smile\",\"video\":false,\"vote_average\":6.8,\"vote_count\":223},{\"adult\":false,\"backdrop_path\":\"/2k9tBql5GYH328Krj66tDT9LtFZ.jpg\",\"genre_ids\":[53,12,27],\"id\":760741,\"original_language\":\"en\",\"original_title\":\"Beast\",\"overview\":\"Le Dr. Nate Daniels, revient en Afrique du Sud, o?? il a autrefois rencontr?? sa femme aujourd???hui d??c??d??e, pour y passer des vacances pr??vues de longue date avec ses deux filles dans une r??serve naturelle, tenue par Martin Battles, un vieil ami de la famille, biologiste sp??cialiste de la vie sauvage. Mais ce repos salvateur va se transformer en ??preuve de survie quand un lion assoiff?? de vengeance, unique rescap?? de la traque sanguinaire d???ignobles braconniers, se met ?? d??vorer tout humain sur sa route et prend en chasse le docteur et sa famille.\",\"popularity\":1882.318,\"poster_path\":\"/kmWpbWYu4gT7wIV4amP0gMDjNHj.jpg\",\"release_date\":\"2022-08-11\",\"title\":\"Beast\",\"video\":false,\"vote_average\":7,\"vote_count\":662},{\"adult\":false,\"backdrop_path\":\"/92PJmMopfy64VYjd0HvIQaHGZX0.jpg\",\"genre_ids\":[10751,35,16,28],\"id\":366672,\"original_language\":\"en\",\"original_title\":\"Paws of Fury: The Legend of Hank\",\"overview\":\"Hank est un chien enjou?? qui r??ve d?????tre samoura?? dans un monde o?? ce privil??ge n???est r??serv????? qu???aux chats !  Moqu??, refus?? par toutes les ??coles de samoura??s, il rencontre un gros matou grincheux, un ma??tre guerrier qui finit par accepter de lui enseigner les techniques ancestrales des samoura??s.  L???apprentissage va ??tre rude pour le jeune chien remuant et dissip?? : il faut apprendre ?? manier le sabre, devenir agile comme un chat, ma??triser les arts martiaux, et Hank n???est pas tr??s dou??. Mais pour devenir samoura??, Hank se donne??? un mal de chien !  Quand l???arm??e de chats du Shogun d??ferle sur la ville, le courage et l???astuce de l???apprenti samoura?? vont enfin s???av??rer utiles : ?? chat va barder, il va leur mettre la p??t??e ?? !\",\"popularity\":1711.037,\"poster_path\":\"/6yD3h1jrTbnPvToXhGfOGFw53PV.jpg\",\"release_date\":\"2022-07-14\",\"title\":\"Samoura?? Academy\",\"video\":false,\"vote_average\":6.8,\"vote_count\":144},{\"adult\":false,\"backdrop_path\":\"/rwgmDkIEv8VjAsWx25ottJrFvpO.jpg\",\"genre_ids\":[10749,18],\"id\":744276,\"original_language\":\"en\",\"original_title\":\"After Ever Happy\",\"overview\":\"Le quatri??me et dernier volet des aventures de Tessa et Hardin.  Une r??v??lation sur le pass?? de Tessa ??branle le couple qu'elle forme avec Hardin. Parall??lement, une v??rit?? choquante sur leur famille respective surgit. Les deux amants ne sont pas si diff??rents l'un de l'autre. Tessa n'est plus la gentille fille qu'elle ??tait lorsqu'elle a rencontr?? Hardin, lui, n'est plus que le gar??on lunatique dont elle est tomb??e amoureuse.\",\"popularity\":1567.679,\"poster_path\":\"/zqd0c9uJQ5mjJvieiRN4VkpJzTs.jpg\",\"release_date\":\"2022-08-24\",\"title\":\"After : Chapitre 4\",\"video\":false,\"vote_average\":7,\"vote_count\":410},{\"adult\":false,\"backdrop_path\":\"/jsoz1HlxczSuTx0mDl2h0lxy36l.jpg\",\"genre_ids\":[14,28,35],\"id\":616037,\"original_language\":\"en\",\"original_title\":\"Thor: Love and Thunder\",\"overview\":\"Alors que Thor est en pleine introspection et en qu??te de s??r??nit??, sa retraite est interrompue par un tueur galactique connu sous le nom de Gorr, qui s???est donn?? pour mission d???exterminer tous les dieux. Pour affronter cette menace, Thor demande l???aide de Valkyrie, de Korg et de son ex-petite amie Jane Foster, qui, ?? sa grande surprise, manie inexplicablement son puissant marteau, le Mjolnir. Ensemble, ils se lancent dans une dangereuse aventure cosmique pour comprendre les motivations qui poussent Gorr ?? la vengeance et l???arr??ter avant qu???il ne soit trop tard.\",\"popularity\":1769.509,\"poster_path\":\"/kSMarEm3ESOOr11dzsep2RZ1ClD.jpg\",\"release_date\":\"2022-07-06\",\"title\":\"Thor : Love and Thunder\",\"video\":false,\"vote_average\":6.7,\"vote_count\":4220},{\"adult\":false,\"backdrop_path\":\"/qaTzVAW1u16WFNsepjCrilBuInc.jpg\",\"genre_ids\":[16,28,10751,35,878],\"id\":539681,\"original_language\":\"en\",\"original_title\":\"DC League of Super-Pets\",\"overview\":\"Krypto, le super-chien de Superman, se trouve face ?? un d??fi immense : sauver son ma??tre, enlev?? par Lex Luthor et son mal??fique cochon d???inde Lulu. Pour cela, il devra faire ??quipe avec une bande d???animaux au grand c??ur mais plut??t maladroits.\",\"popularity\":1579.876,\"poster_path\":\"/9Nf7UH8uExdV2Ta4UupmnYjCkYc.jpg\",\"release_date\":\"2022-07-27\",\"title\":\"Krypto et les Super-Animaux\",\"video\":false,\"vote_average\":7.5,\"vote_count\":809}],\"total_pages\":35471,\"total_results\":709420}"

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