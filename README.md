# Balance management software

This is an old program written around 2009 in Java.

It tracks user balances for products. The program is still in active use in places (at least in 2024).

## Features

- Manage users: add, remove, edit
- Track user balances: increase and decrease (sell an item)
- Assign a static discount for users
- Display statistics about product sales (a hidden feature, ask if unsure how to get this visible)

## Instructions

These are old instructions I wrote around 2010, so they may be outdated. Oh, and these are in Finnish.

> Tässä yleiset Piikin asennusohjeet. Näitä tarttee lähinnä vaan jos tarvii asentaa koko Piikki uusiks - normikäytössä näitä ei tarvii.
>
> Tarvitaan pari asiaa:
> 1) muistitikku (max 19GB)
> 2) muistitikulla tiedostot jcom.jar, jfree.jar, mysql.jar ja Piikki.jar . Voivat hyvin olla hieman eri nimillä tai pidemmillä nimillä mutta kulkevat käsi kädessä nuo kaikki neljä.
> 3) koneella asennettuna mysql ja sinne lisätty tietokanta ja sille käyttäjä (oletan jatkossa että tietokannan nimi on piikkidb ja käyttäjän nimi piikkiuser ja käyttäjän salasana on X)
> 4) ohjelma nimeltään mysqldump.exe joko siten että sen pystyy käynnistämään kaikkialta Windowsista tai sitten kopioi se tuohon samaan paikkaan tikulle. Löytyy jostain sieltä mysql-asennuksen bin-kansiosta.
> 5) tietokoneella asennettuna java-tulkki
>
> Kun tämä on kaikki tehty, ohjelman saa auki seuraavasti:
> 1) avaa windows command prompt
> 2) mene siihen tikun hakemistoon mihin työnsit nuo piikkitiedostot
> 3) kirjoita: java -cp jcom.jar;jfree.jar;mysql.jar;Piikki.jar piikki.GUI -db=piikkidb -user=piikkiuser -pw=X
> 4) voit pistää tuon taikaloitsun myös esim tiedostoon piikki.bat ja luoda siihen pikakuvakkeen.
>
> Kannattaa aina vuodenvaihteessa tyhjentää sen tietokannan purchases ja adminlog -taulut. Ne vievät eniten tilaa ja varmuuskopioiden koko kasvaa jatkuvasti ja vievät siten eksponentiaalisesti tilaa tikulta. Ainakin jos varmuuskopioiden koko kasvaa yli megan niin kannattaa tyhjennellä noita, mutten vähän hidastuu toi käynnistys ja sulkeminen