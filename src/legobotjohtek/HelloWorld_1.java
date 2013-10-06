package legobotjohtek;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

// ALA MUUTA LUOKAN NIMEA
public class HelloWorld {

    static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B); // kokeillaan nailla arvoilla
    static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);                  // tarkista portit robotista
    static LightSensor light = new LightSensor(SensorPort.S1);

    public static void main(String[] args) {

        /*
         Yleinen lejos API: http://lejos.sourceforge.net/nxt/nxj/api/index.html
	  
         Valosensori lukee taustavaloa ja on osoitettuna robotin edessa suoraan maahan. Voit olettaa, 
         etta viivan taustavalon voimakkuus on joko reilusti muuta suurempi tai pienempi. Tee kuitenkin
         koodistasi sellainen, etta viivan ja muun lattian taustavalon voimakkuuden voi helposti vaihtaa
         keskenaan (eli seurataanko tummaa vai vaaleaa viivaa).
	  
         Valosensorin API: http://lejos.sourceforge.net/p_technologies/nxt/nxj/api/lejos/nxt/LightSensor.html			
	  
         Valosensorin kalibrointi nykyisiin arvoihin
         light.calibrateLow();
         light.calibrateHight();
	  
         Valosensorin taustavalon voimakkuuden mittaus
         light.readValue();
	  
         Robotti liikkuu yksinkertaisilla komennoilla eteen ja taaksepain seka kaantyy akselinsa ympari.
         Jos haluat, voit kayttaa muitakin komentoja API:sta, mutta muista etta et paase testaamaan ennen
         mahdollista ajoa.


         Motor-olioiden avulla liikkuminen
	  
         void forward()           // Liikuttaa eteenpain
         void backward()          // Liikuttaa taaksepain
         boolean isMoving()       // Kysyy onko liikkeessa
         void setSpeed(int speed) // Asettaa pyorimisnopeuden (max 900)
         void stop()              // Pysayttaa moottorin

         Kaytannossa setSpeed() samaksi molemmille moottoreille.


         Pilotin avulla liikkuminen
	  
         Liiku tietty maara sentteja suoraan (minus liikkuu taaksepain)
         pilot.travel(cm);
	  
         Liiku eteenpain kunnes x (esim. while loopin sisalla)
         pilot.forward();
	  
         Liiku taaksepain kunnes x
         pilot.backward();
	  
         Liikkumis nopeus cm / sekunti
         pilot.setTravelSpeed(sentteja sekunnissa);
	  
         Kaantymis nopeus asteita / sekunti.
         pilot.setRotateSpeed(asteita sekunnissa);
	  
         Jos haluat kaantya vallitsevasta asennosta tietyn maaran niin kayta tata.
         pilot.rotate(asteet, true); // true meinaa ettei jaada odottamaan
	  
         pilot.rotateTo(kulma, true); // kaanny tiettyyn kulmaan
	
         Aseta nama ensin
         pilot.setRotateSpeed(20);
         pilot.setTravelSpeed(15);
         */

        // Pida tama, niin ohjelma ei ala suorittaa liikkumista heti, vaan voit rauhassa irrottaa
        // USB-kaapelin, yms.
        Button.waitForAnyPress();

        // lisaa viivan seurauskoodi tahan.
        // Voit olettaa viivan paksuuden olevan vahintaan 2cm ja
        // etta viiva ei tee jyrkkia kaarteita.

        // Ota huomioon mahdolliset muuttuvat olosuhteet jne.
        // Ala siis sorru kikkailuun vaan tee mahdollisimman selkea ja helppo koodi.
        // (Viivaa voi seurata yllattavan helpoilla komennoilla!)
        // Huomaa lisaksi, etta valosensori on noin 10 cm akselista eteenpain (ks. kuva 
        // laskuharjoitustehtavanannossa).



        //taskuparkkibotti
        int arvo = 0;
        boolean v = false;
        boolean aukossa = false;
        //muuta oikea metodin nimi
        int etaisyys = sonic.luearvotmsmetodi;

        pilot.setTravelSpeed(1);
        pilot.setRotateSpeed(1);

        while (true) {
            //etsitään aukko ja ajetaan eteenpäin kunnes aukon reuna lopuilla :)

            while (v == false) {
                pilot.forward();
                if (aukossa) {
                    arvo++;
                }
                if (sonichavaitseeaukon) {
                    aukossa = true;
                }
                if (aukossa && soniceihavaitse_enää && arvo < 40) {
                    arvo = 0;
                    aukossa = false;
                }
                if (aukossa && arvo > 40) {
                    while (sonichavaitsee) {
                        pilot.forward();
                    }
                    break;
                }
            }
            //käänny ja peruuta ja käänny

            pilot.rotate(45, true);
            pilot.travel(etaisyys * -1);
            pilot.rotate(-45, true);
        }
    }

    //loppuwait
    Button.waitForAnyPress ();
}
}
