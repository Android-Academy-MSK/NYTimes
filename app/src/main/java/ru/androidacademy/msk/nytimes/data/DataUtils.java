package ru.androidacademy.msk.nytimes.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public final class DataUtils {

    public static List<NewsItem> generateNews() {
        final Category darwinAwards = new Category(1, "Darwin Awards");
        final Category criminal = new Category(2, "Criminal");
        final Category animals = new Category(3, "Animals");
        final Category music = new Category(4, "Music");

        final List<NewsItem> news = new ArrayList<>();
        news.add(new NewsItem(
                "Tourist filmed sitting on 5m-long crocodile",
                "https://e3.365dm.com/18/09/736x414/skynews-crocodile-australia_4433218.jpg",
                darwinAwards,
                createDate(2018, 9, 26, 10, 34),
                "\"It was dangerous, I know. It is a scary feeling sitting on something that could kill you in a fraction of a "
                        + "second,\" he says.",
                "A Danish tourist has admitted he took his life in his hands by sitting on a large crocodile in Australia.\n\n"
                        + "Niels Jensen, 22, was on safari in a wildlife park east of Darwin in northern Australia when he "
                        + "encountered the predator, estimated to be 4.7m (15ft) long and weighing 653kg (1428 lbs).\n\n"
                        + "The wildlife management graduate is filmed enticing the large reptile, which had been relocated to the "
                        + "park after it was caught preying on livestock, with a wallaby carcass."
                        + "After leaving the bait on the ground and waiting for the crocodile to start eating, he astonishingly "
                        + "straddled the reptile's back, sitting just behind its rear legs.\n\n"
                        + "He touched some of the scales on the animal's back, and, after a few moments, rose and walked away.\n\n"
                        + "But a man out of shot told him to get back and give a thumbs-up, so he approached the animal for a second"
                        + " time, sat down again, turned towards the camera, smiled and put this thumb in the air.\n\n"
                        + "Mr Jensen admitted he took life in his hands by sitting on a live crocodile for the first time."
        ));
        news.add(new NewsItem(
                "Police warn daredevil cliff jumpers who are 'risking their lives for likes'",
                "https://e3.365dm.com/18/09/2048x1152/skynews-cliff-jumping-greg-milam_4433647.jpg",
                criminal,
                createDate(2018, 9, 25, 12, 45),
                "Police in Los Angeles say they are spending hundreds of thousands of dollars airlifting cliff jumpers out of "
                        + "dangerous spots.",
                "Daredevils attempting dangerous cliff dives in a quest for likes has led to an increase in costly helicopter "
                        + "airlifts in California, police say.\n\n"
                        + "As young people pursue the perfect selfie or video for their social media pages, the Los Angeles County "
                        + "Sheriff's Department says it is spending hundreds of thousands of dollars plucking the injured and "
                        + "stranded from beauty spot locations. \"People have to understand: people die up in those mountains. "
                        + "For every rescue you see that we do, there are ones that we don't make. They're dead,\" said Deputy "
                        + "Stephen Doucette.\n\n"
                        + "A social media search for locations like Eaton Canyon, Hermit Falls and Malibu Creek Rock Pool reveal "
                        + "dozens of risky selfie videos. Two men were recently rescued after being injured while being filmed at "
                        + "Hermit Falls."
        ));
        news.add(new NewsItem(
                "Bear saved after getting his head stuck in milk can",
                "https://e3.365dm.com/18/09/2048x1152/skynews-bear-minnesota_4419111.jpg",
                animals,
                createDate(2018, 9, 20, 14, 4),
                "Firefighters used the Jaws of Life to free the young black bear, a tool which is normally used to extricate car"
                        + " accident victims.",
                "A bear has been freed after getting his head stuck in a milk can.\n\n"
                        + "Firefighters were called to help after a conservation officer encountered the grizzly sight in "
                        + "Minnesota.\n\n"
                        + "The young black male bear's head was stuck inside an old 10 gallon (38 litre) milk can.\n\n"
                        + "At first, rescuers tried to use cooking oil to free the animal. When that didn't work, they drilled three"
                        + " holes in the milk can so the panting bear could breathe.\n\n"
                        + "Two hours later, firefighters used the \"Jaws of Life\" - a tool which is normally used to extricate car "
                        + "accident victims - and a spreader to pry the can off.\n\n"
                        + "After being released, the seemingly healthy bear ran off into the woods."
        ));
        news.add(new NewsItem(
                "Nearly $18m of cocaine seized in donated boxes of bananas",
                "https://e3.365dm.com/18/09/2048x1152/skynews-texas-bananas-drugs_4430760.jpg",
                criminal,
                createDate(2018, 9, 18, 4, 4),
                "Massive quantities of the drug were found in boxes of fruit that had been donated to the Texas Department of "
                        + "Criminal Justice.",
                "A huge haul of cocaine was discovered hidden in boxes of bananas donated to the Texas Department of Criminal "
                        + "Justice.\n\n"
                        + "Some 45 boxes of bananas from Ports of America in Freeport were given away to the agency because they "
                        + "were already ripe.\n\n"
                        + "According to a Facebook post on the TDCJ's page, when two sergeants of the Scott Unit arrived to pick "
                        + "them up they \"discovered something not quite right\".\n\n"
                        + "The post explains: \"One of the boxes felt different than the others.\n\n"
                        + "\"They snipped the straps, pulled free the box, and opened it up.\n\n"
                        + "\"Inside, under a bundle of bananas, he found another bundle! Inside that? What appeared to be a white "
                        + "powdery substance.\n\n"
                        + "\"They immediately notified port authorities and awaited their instruction.\""
                        + "US Customs arrived and tested the substance, which confirmed the powder was cocaine."
        ));
        news.add(new NewsItem(
                "US government hacker jailed after losing secrets",
                "https://e3.365dm.com/17/09/736x414/d55722dc4eb37f6959d2e047c14710d586aab99f90aa1e4acfd9f992125294f5_4107038.jpg",
                criminal,
                createDate(2018, 9, 17, 12, 45),
                "Nghia Hoang Pho, 68, who developed hacking tools for the National Security Agency, illegally stored material "
                        + "on his home computer.",
                "A man who illegally took home hacking tools from his workplace at the National Security Agency, and then "
                        + "allegedly lost them to Russian intelligence, has been jailed for five years and six months.\n\n"
                        + "Nghia Hoang Pho, 68, developed hacking tools at the NSA's elite Tailored Access Operations (TAO) unit, "
                        + "which works on penetrating target computer networks for the US intelligence community.\n\n"
                        + "While employed by the NSA between 2010 and 2015, Pho took home what prosecutors described as \"massive "
                        + "troves of highly classified national defence information\" and stored those troves on his home computer "
                        + "network.\n\n"
                        + "Reports have alleged that while these tools were stored on his home computer, Pho installed Kaspersky "
                        + "Lab anti-virus software, which Russian intelligence then used to steal those tools for themselves.\n\n"
                        + "Although the company has vigorously denied claims its software was used by Russian intelligence to steal"
                        + " the data, the publicity damage has left Kaspersky Lab working to address customer fears in a global "
                        + "transparency initiative - including moving a significant portion of its operations from Russia to "
                        + "Switzerland.\n\n"
                        + "An internal investigation at the cyber security company into the incident prompted the company to suggest"
                        + " that an NSA employee had actually been hacked when he downloaded pirate software and disabled "
                        + "Kaspersky's anti-virus."
        ));
        news.add(new NewsItem(
                "Wet Wet Wet announce Liberty X star Kevin Simm as new frontman",
                "https://e3.365dm.com/18/09/2048x1152/skynews-wet-wet-wet-kevin-simm_4433314.jpg",
                music,
                createDate(2018, 9, 17, 12, 45),
                "The Voice 2016 winner says he was \"really taken aback\" by the opportunity after singing the band's songs "
                        + "early in his career.",
                "The Scottish band, who are best-known for their 1994 cover of The Troggs' 1960s hit Love Is All Around, "
                        + "revealed the change in line-up on Tuesday.\n\n"
                        + "Simm, 38, who won The Voice in 2016, will take over singing duties after founding member Marti Pellow "
                        + "left the band last year.\n\n"
                        + "Simm, from Lancashire, first shot to fame on ITV talent show Popstars in 2001 after forming the group "
                        + "Liberty X with four other runner-up contestants.\n\n"
                        + "He has recalled singing Wet Wet Wet's songs early in his career.He said: \"I was really taken aback, the"
                        + " opportunity to join a band with such amazing songs and great guys and a great fanbase really "
                        + "excites me.\n\n"
                        + "\"When I first started gigging around the pubs and clubs up North, two songs that were always in my set "
                        + "were Goodnight Girl and Love Is All Around.\""
        ));

        return news;
    }

    private static Date createDate(int year, int month, int date, int hrs, int min) {
        return new GregorianCalendar(year, month - 1, date, hrs, min).getTime();
    }

    private DataUtils() {
        throw new AssertionError("No instances");
    }
}
