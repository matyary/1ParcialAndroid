package Fragments

import Entities.Sport
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso

import com.utn.tp3.R
import database.appDatabase
import database.sportDao
import java.util.Observer

/**
 * A simple [Fragment] subclass.
 */
class FragmentSelect : Fragment() {

    lateinit var btn_aerobico: Button
    lateinit var btn_musculacion: Button
    lateinit var btn_flexibilidad: Button
    lateinit var view_fselect: View

    private var db: appDatabase? = null
    private var sportDao: sportDao? = null

    private var flagSelect: Int=0

    val args: FragmentSelectArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view_fselect = inflater.inflate(R.layout.fragment_select, container, false)

        btn_aerobico = view_fselect.findViewById(R.id.btn_a)
        btn_musculacion = view_fselect.findViewById(R.id.btn_m)
        btn_flexibilidad = view_fselect.findViewById(R.id.btn_f)

        db = appDatabase.getAppDataBase(view_fselect.context)
        sportDao = db?.sportDao()

        if (sportDao?.verifyEmptyTable() == 0) {
            sportDao?.insertSport(Sport(0,
                    "BURPEES",
                    "Trabajas fuerza, resistencia y capacidad aeróbica a través de 4 movimientos sencillos\n" +
                            "1. Cuclillas con manos en el suelo y cabeza erguida.\n" +
                            "2. En un movimiento rápido dirige ambas piernas hacia atrás y queda en posición de plancha.\n" +
                            "3. Haz una flexión (o lagartija) y regresa a la posición de plancha.\n" +
                            "4. Levántate y haz un salto con una palmada en el aire.",
                    "3 series de 20.",
                    "https://www.sportlife.es/media/cache/big/upload/images/article/5b3f1200a3fea541e6dfe4e2/5c51c3b10ce69424548b4856-burpees-tecnica-perfecta.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(1,
                    "JUMPING JACKS",
                    "Con este ejercicio mejoras agilidad y coordinación en brazos y piernas.\n" +
                            "En la posición de inicio abre las piernas a la anchura de los hombros. Junta las piernas con un " +
                            "leve salto, mientras levantas los brazos a la vez para que ambas manos toquen detrás de la cabeza. " +
                            "La cabeza va recta y la vista al frente.",
                    "3 series de 20.",
                    "https://ejerciciosdegimnasio.com/wp-content/uploads/2018/02/2-como-hacer-jumping-jacks-para-bajar-de-peso-6578-2.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(2,
                    "MOUNTAIN CLIMBER",
                    "Este ejercicio es efectivo para trabajar el tren inferior del cuerpo, además de que te ayudará a activar tu metabolismo.\n" +
                            "Para ejecutarlo colócate en posición de plancha, manos estiradas, espalda ecta, puntas de los pies bien apoyadas en el piso. " +
                            "El siguiente paso es dirigir una de las piernas hacia el pecho y alternar. Es decir, la pierna que está estirada pasa hacia" +
                            "delante y así sucesivamente.",
                    "3 series de 20.",
                    "https://www.mujerde10.com/wp-content/uploads/2018/09/Mountain-Climber.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(3,
                    "SKATER EXERCISE",
                    "Se le conoce también como salto alterno de piernas o skater exercise por su nombre en inglés. Con este movimiento cardio" +
                            "trabajas cuadriceps, gemelos, glúteos y zona media del cuerpo.\n" +
                            "Para comenzar este ejercicio colócate de pie con las piernas ligeramente separadas y las rodillas flexionadas, torso" +
                            "levemente inclinado hacia adelante pero con la espalda recta.\n" +
                            "Comienza el movimiento saltando hacia el lado izquierdo. Aterriza siempre con la rodilla flexionada, pasa la pierna" +
                            "derecha por atrás, colocando el pie por atrás de la pierna de apoyo.\n" +
                            "Inmediatamente después de que el pie derecho toque el piso, salta al lado derecho y repite el movimiento de forma alternada" +
                            "con una y otra pierna.",
                    "3 series de 20.",
                    "https://www.liferenu.com/wp-content/uploads/2019/09/Skater-Jumps.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(4,
                    "HIGH KNEES",
                    "Se ejecuta como si estuvieras trotando, pero elevando las rodillas hasta la altura del pecho. Este ejercicio es" +
                            "de una gran eficacia para perder peso, además de que es muy sencillo de realizar.\n" +
                            "Para hacer tus elevaciones de rodilla correctamente coloca los pies a lo ancho de tus caderas y con los brazos cerca del" +
                            "cuerpo. Levanta la rodilla izquierda hacia el pecho, exhalando y contrayendo los abdominales. Al mismo tiempo, dobla los" +
                            "codos y lleva los puños delante de los hombros. Vuelve a la posición inicial y repite con la pierna contraria.",
                    "3 series de 20.",
                    "https://pasionrunner.com/wp-content/uploads/2019/06/Ejercicio-9.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(5,
                    "SEAL JUMPS",
                    "Parecido a Jumping Jacks, pero esta vez realizamos un aplauso al llevar las manos hacia el centro.",
                    "3 series de 20.",
                    "https://fitnessbit.net/wp-content/uploads/2018/03/Seal-Jacks.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(6,
                    "ALTERNING FAST FEET",
                    "Realizamos pequeños saltos de manera muy rápida (o lo más rápido posible queriendo mantener el ejercicio" +
                            "por un período de tiempo más prolongado) alternando los pies.",
                    "3 series de 20.",
                    "https://virallifee.com/wp-content/uploads/2019/06/Fast-Feet.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(7,
                    "TURKISH GET UP",
                    "Es un ejercicio que suele realizarse con mancuerna, aunque tranquilamente lo pueden hacer con cualquier" +
                            "otro elemento, o incluso sin peso en caso de que nunca lo hayan hecho.",
                    "3 series de 15.",
                    "https://elrincondelsano.com/wp-content/uploads/2016/06/turkish-get-up.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(8,
                    "SPRINT",
                    "El sprint (o carrera) es un ejercicio en el que se descarga una cantidad de energía muy grande de forma muy" +
                            "rápida, es importante hacerlo con un calzado adecuado y en una superficie que nos permita pisar correctamente," +
                            "recuerda hacerlo intensamente durante toda su duración.",
                    "3 series de 20 segundos.",
                    "https://www.calistenia.net/wp-content/uploads/2018/02/sprinting.jpeg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(9,
                    "FLUTTER KICKS",
                    "También llamado mariposas. Es un ejercicio que causa una gran carga sobre varios grupos musculares" +
                            "del cuerpo, además al tener un movimiento constante y rápido también es muy buen ejercicio de cardio.",
                    "3 series de 15.",
                    "https://okdiario.com/img/vida-sana/2016/01/Flutter-Kicks-526x421.jpg",
                    "AEROBICO"))
            sportDao?.insertSport(Sport(10,
                    "FLEXIONES DE BRAZOS",
                    "En este tipo de actividad se trabaja los bíceps, los tríceps y los pectorales de manera más intensa." +
                            "Para aquellas personas que no tengan aún la fuerza suficiente en los brazos, se pueden realizar medias flexiones" +
                            "apoyando en cuerpo en las rodillas y no en la punta de los pies.\n" +
                            "Ponte boca abajo en el suelo, apoya el peso en la punta de los pies y eleva el torso con los brazos." +
                            "Coloca las piernas juntas y las manos con una separación de 10 cm, lo que equivale a algo más cerca de" +
                            "la anchura de los hombros.\n" +
                            "El cuerpo debe quedar recto, sin arquearse en la zona de la lumbar ni en el cuello. A continuación flexiona" +
                            "los brazos de modo que la cabeza casi toque el suelo, vuelve a la posición inicial.",
                    "3 series de 15.",
                    "https://3.bp.blogspot.com/-5EA0yxWu1qQ/UEOhciWYEbI/AAAAAAACnII/4CJSl62-N08/s1600/flexiones.jpeg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(11,
                    "SENTADILLAS",
                    "Los músculos que realizan la fuerza en las sentadillas son el cuádriceps y el glúteo mayor. Con estos ejercicios" +
                            "también trabajarás los gemelos y el erector espinal, encargado de sostener el peso del tren superior.\n" +
                            "Debes ponerte de pie, mirando al frente y con las piernas ligeramente separadas. Coloca los brazos hacia delante" +
                            "como si fueras a abrazar a alguien; esto favorecerá el equilibrio. Con el torso recto, flexiona las rodillas hasta" +
                            "que los glúteos lleguen a la altura de los gemelos. Vuelve a la posición inicial y repite.",
                    "3 series de 20.",
                    "https://deportesyeducacionfisica.com/wp-content/uploads/2012/03/hacer-sentadillas-correctamente-600x399.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(12,
                    "SENTADILLAS PLIOMETRICAS",
                    "Esta es una divertida forma de hacer sentadillas y liberar tensiones. Están implicados músculos de todo el cuerpo," +
                            "desde las piernas hasta los brazos, pasando por el pecho. Consiste en hacer una sentadilla tradicional acabando con" +
                            "un gran salto hacia arriba.\n" +
                            "Deberás colocarte en la posición de sentadilla, inclinar levemente el torso hacia adelante, flexionar las piernas" +
                            "hasta que los glúteos lleguen a los gemelos y, en este momento, saltar hacia arriba extendiendo los brazos al mismo tiempo.",
                    "3 series de 20.",
                    "https://atopedegym.com/wp-content/uploads/2014/07/sentadilla-con-salto.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(13,
                    "STEP O SUBIDA DE ESCALON",
                    "Para este ejercicio se necesita un taburete, un banco o un escalón alto. Se trabajarán músculos similares a los de las sentadillas:" +
                            "cudriceps y glúteo superior. Según la posición que adopten los brazos también se ejercitará los tríceps.\n" +
                            "De esto modo, debes colocarte frente al banco, apoyando uno de los pies encima de éste. El otro estará en contacto con el suelo y" +
                            "con la pierna completamente extendida. Los brazos se colocarán como en las sentadillas, hacia delante.\n" +
                            "Con la pierna flexionada deberás empujar hacia arriba el resto del cuerpo. Cuando la pierna apoyada en el banco esté completamente" +
                            "extendida, vuelve a la posición inicial y repite el movimiento. Es importante mantener todo el cuerpo recto.",
                    "3 series de 15.",
                    "https://i.pinimg.com/600x315/d8/de/4e/d8de4e66a24b58834b3837b1a7002875.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(14,
                    "ELEVACION DE PELVIS",
                    "En este ejercicio se encuentran implicados músculos de las piernas, la espalda y los abdominales. Necesitarás una superficie donde tumbarte;" +
                            "ponerte en el suelo sobre una manta o una esterilla es buena opción.\n" +
                            "Túmbate boca arriba con las piernas extendidas y los brazos perpendiculares al cuerpo. Deberás elevar la pelvis con la ayuda de las piernas" +
                            "hasta que las rodillas formen un ángulo de 90º. Aguanta unos segundos en esta posición y vuelve lentamente a la inicial.",
                    "3 series de 20.",
                    "https://xoomproject.com/blog/wp-content/uploads/elevacion-de-pelvis.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(15,
                    "DOMINADAS",
                    "Para la realización de este ejercicio se necesita una barra donde colgarse; en infinidad de parques existen zonas con estas barras." +
                            "Para practicarse en casa se puede habilitar una barra y colocarla en el pasillo o en el marco de alguna puerta.\n" +
                            "Éste es uno de los ejercicios más completos y en el que más resistencia ejerce nuestro propio peso. Se trabajan músculos de los" +
                            "brazos, los hombros, las abdominales y la espalda. La dificultad yace en que se debe levantar el propio peso corporal.\n" +
                            "Deberás colgarte de la barra con los brazos algo separados y las manos hacia el frente. A continuación flexiona las rodillas" +
                            "y cruza los pies; en este momento estarás en el aire, sostenido sólo por los brazos.\n" +
                            "Sube el cuerpo hasta que la barbilla supere la barra y después vuelve a la posición inicial lentamente.",
                    "3 series de 20.",
                    "https://4.bp.blogspot.com/-BGOz86n__MI/WbHvGxkB3GI/AAAAAAAAVXY/k-PvXnks1REMFBSJl9Ya6UVYdGiNhhfqACLcBGAs/s1600/dominadas-supinacion.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(16,
                    "ABDOMINALES CLÁSICOS",
                    "Para finalizar con la rutina de entrenamiento puedes realizar un par de series de 30 de abdominales clásicas." +
                            "Con este tipo ejercicio se trabaja la pared abdominal superior.\n" +
                            "Debes tumbarte en el suelo, flexionar ligeramente las rodillas y colocar las manos detrás de la nuca. A continuación" +
                            "debes elevar el tronco ejerciendo la fuerza con los abdominales, nunca con las cervicales ni con los brazos. Cuando la" +
                            "contracción de los abdominales sea adecuada, vuelve a la posición inicial.",
                    "3 series de 30.",
                    "https://fitmlg.files.wordpress.com/2016/06/crunchfitmlg.jpg",
                    "MUSCULACION"))
            sportDao?.insertSport(Sport(17,
                    "TORSION DE ESPALDA",
                    "Sentado en el suelo. Pierna derecha estirada. Dobla la izquierda y pásala por encima de la derecha. Coloca el brazo" +
                            "izquierdo por encima de la rodilla flexionada y presiona con el codo para hacer una torsión de espalda. Siente el" +
                            "estiramiento. Repite al otro lado.",
                    "1 serie de 30 segundos por cada lado.",
                    "https://3.bp.blogspot.com/-OOpJY3Wev0o/Tvxfr70ofaI/AAAAAAAAAko/fmha4fJQU8Q/s1600/Vakrasana.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(18,
                    "SENTADO",
                    "Estira la pierna derecha y flexiona la izquierda llevando el pie hacia la ingle. Mantén el equilibrio. Inclina el" +
                            "cuerpo hacia delante para intentar tocar los dedos de los pies. Cambia de pierna.",
                    "1 serie de 30 segundos por cada lado.",
                    "https://www.foroatletismo.com/imagenes/2013/07/Rutina-estiramientos-isquiotibiales.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(19,
                    "MUSLOS Y CADERA",
                    "Tumbado boca abajo. Flexiona una pierna y coge el pie con la mano del mismo lado. Tira de ella todo lo que puedas sin que el" +
                            "muslo se despegue del suelo. Cambia de pierna.",
                    "1 serie de 30 segundos por cada lado.",
                    "https://www.marchasyrutas.es/blog/wp-content/uploads/2017/12/flexibilidad-bicicleta.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(20,
                    "LUMBARES",
                    "Estira una pierna y encoge la otra cogiéndote de la rodilla y tirando de ella hacia el pecho. La pierna estirada no debe levantarse" +
                            "del suelo. Cambia de pierna.",
                    "1 serie de 30 segundos por cada lado.",
                    "https://mundoentrenamiento.com/wp-content/uploads/2017/06/estiramiento-alivia-dolor-lumbar-888x500.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(21,
                    "ABDUCTORES",
                    "Sentado en el suelo. Abre las piernas estiradas lo más que puedas e inclina el torso hacia delante, sin doblar las rodillas. Estira los brazos" +
                            "e intenta bajar un poco más.",
                    "1 serie de 30 segundos.",
                    "https://t2.uc.ltmcdn.com/images/0/2/8/estiramiento_para_aumentar_la_flexibilidad_de_la_espalda_49820_10_600.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(22,
                    "ZANCADA EN SOFA",
                    "Una pierna se adelanta, como en las zancadas normales, con la rodilla en 90 grados y la otra está estirada hacia atrás, pero apoyada en un sofá o en" +
                            "una silla. Es difícil mantener la posición. Aguanta y cambia de lado.",
                    "1 serie de 30 segundos.",
                    "https://i.blogs.es/bb7de2/istock-519531814/450_1000.jpg",
                    "FLEXIBILIDAD"))
            sportDao?.insertSport(Sport(23,
                    "EL PUENTE",
                    "Es una postura de yoga y estira columna, pecho, cuello y hombros. Boca arriba en el suelo con las rodillas flexionadas. Levanta la pelvis y el torso hasta" +
                            "los hombros. Estira los hombros hacia abajo para que queden bien apoyados en el suelo. Mirada al techo y brazos estirados y apoyados en el suelo.",
                    "1 serie de 30 segundos.",
                    "https://www.yoamoloszapatos.com/wp-content/uploads/2018/07/AdobeStock_83150377-620x414.jpeg",
                    "FLEXIBILIDAD"))
        }

        return view_fselect
    }

    override fun onStart() {
        super.onStart()

        if (args.sportToErase != null){
            sportDao?.deleteSport(args.sportToErase!!)
            flagSelect = 1
        }
        else flagSelect = 0

        btn_aerobico.setOnClickListener{
            val action = FragmentSelectDirections.actionFragmentSelectToListFragment(1, flagSelect)
            view_fselect.findNavController().navigate(action)
        }

        btn_musculacion.setOnClickListener{
            val action = FragmentSelectDirections.actionFragmentSelectToListFragment(2, flagSelect)
            view_fselect.findNavController().navigate(action)
        }

        btn_flexibilidad.setOnClickListener{
            val action = FragmentSelectDirections.actionFragmentSelectToListFragment(3, flagSelect)
            view_fselect.findNavController().navigate(action)
        }
    }
}
