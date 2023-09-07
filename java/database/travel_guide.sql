BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, city, user_likes, likes, hotel, restaurant, bar, events, tourism;

CREATE TABLE users (
	user_id serial NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(50),
	role varchar(50) NOT NULL,
    activated boolean NOT NULL DEFAULT false, -- Add this line
	CONSTRAINT pk_user PRIMARY KEY (user_id),
	CONSTRAINT uq_username UNIQUE (username)
);

CREATE TABLE city (
	city_id serial NOT NULL,
	city_name varchar(50) NOT NULL,
	state_name varchar(50),
	cover_picture varchar(250) NOT NULL,
	city_video varchar(250),
	city_description varchar(2500) NOT NULL,
	CONSTRAINT pk_city PRIMARY KEY (city_id),
	CONSTRAINT pk_state UNIQUE (state_name)
);

CREATE TABLE hotel (
	hotel_id serial NOT NULL,
	hotel_city_id INT NOT NULL,
	hotel_picture varchar(250) NOT NULL,
	hotel_city_name varchar(50) NOT NULL,
	hotel_name varchar(500) NOT NULL,
	hotel_website varchar(1000) NOT NULL,
	hotel_address varchar(1000) NOT NULL,
	hotel_phone varchar(20) NOT NULL,
	hotel_price varchar(5) NOT NULL,
	CONSTRAINT pk_hotel PRIMARY KEY (hotel_id),
	CONSTRAINT fk_hotel_city FOREIGN KEY(hotel_city_id) REFERENCES city(city_id)
);

CREATE TABLE restaurant (
	restaurant_id serial NOT NULL,
	restaurant_city_id int NOT NULL,
	restaurant_picture varchar(250) NOT NULL,
	restaurant_name varchar(500) NOT NULL,
	restaurant_website varchar(1000) NOT NULL,
	restaurant_address varchar(1000) NOT NULL,
	restaurant_phone varchar(20) NOT NULL,
	restaurant_price varchar(5) NOT NULL,
	CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id),
	CONSTRAINT fk_restaurant_city FOREIGN KEY(restaurant_city_id) REFERENCES city(city_id)
);

CREATE TABLE bar (
	bar_id serial NOT NULL,
	bar_city_id INT NOT NULL,
	bar_picture varchar(250) NOT NULL,
	bar_name varchar(150) NOT NULL,
	bar_website varchar(1000) NOT NULL,
	menu_link varchar(500) NOT NULL,
	bar_address varchar(150) NOT NULL,
	bar_phone varchar(20) NOT NULL,
	bar_price varchar(5) NOT NULL,
	CONSTRAINT pk_bar PRIMARY KEY (bar_id),
	CONSTRAINT fk_bar_city FOREIGN KEY(bar_city_id) REFERENCES city(city_id)
);

CREATE TABLE events (
	event_id serial NOT NULL,
	event_city_id INT NOT NULL,
	event_name varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	event_address varchar(50) NOT NULL,
	event_phone varchar(12) NOT NULL,
	event_price varchar(5) NOT NULL,
	CONSTRAINT pk_event PRIMARY KEY (event_id),
	CONSTRAINT fk_event_city FOREIGN KEY(event_city_id) REFERENCES city(city_id)
);

CREATE TABLE tourism (
	tour_id serial NOT NULL,
	tour_city_id INT NOT NULL,
	tour_name varchar(50) NOT NULL,
	tour_address varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	tour_price varchar(5) NOT NULL,
	CONSTRAINT pk_tour PRIMARY KEY (tour_id),
	CONSTRAINT fk_tour_city FOREIGN KEY(tour_city_id) REFERENCES city(city_id)
);

CREATE TABLE likes (
    like_id serial NOT NULL,
    like_city_id int,
    like_bar_id int,
    like_hotel_id int,
    like_restaurant_id int,
    like_event_id int,
    like_tour_id int,
	like_picture varchar(250) NOT NULL,
	like_city_name varchar(50) NOT NULL,
    like_name varchar(500) NOT NULL,  -- Common name attribute
    like_address varchar(1000) NOT NULL,  -- Common address attribute
    like_phone varchar(20) NOT NULL,  -- Common phone attribute
    like_price varchar(5) NOT NULL,  -- Common price attribute
    like_website varchar(1000) NOT NULL,  -- Common website attribute
    CONSTRAINT pk_like PRIMARY KEY (like_id),
    CONSTRAINT fk_like_city_id FOREIGN KEY (like_city_id) REFERENCES city(city_id),
    CONSTRAINT fk_like_bar_id FOREIGN KEY (like_bar_id) REFERENCES bar(bar_id),
    CONSTRAINT fk_like_hotel_id FOREIGN KEY (like_hotel_id) REFERENCES hotel(hotel_id),
    CONSTRAINT fk_like_restaurant_id FOREIGN KEY (like_restaurant_id) REFERENCES restaurant(restaurant_id),
    CONSTRAINT fk_like_event_id FOREIGN KEY (like_event_id) REFERENCES events(event_id),
    CONSTRAINT fk_like_tour_id FOREIGN KEY (like_tour_id) REFERENCES tourism(tour_id)
);

CREATE TABLE user_likes (
	user_id int NOT NULL REFERENCES users(user_id),
	like_id int NOT NULL,
	CONSTRAINT pk_user_likes PRIMARY KEY (user_id, like_id),
	CONSTRAINT fk_user_like_like FOREIGN KEY (like_id) REFERENCES likes(like_id)
);

-- CITY
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Pittsburgh', 'Pennsylvania','https://cdn.wallpapersafari.com/34/60/pwHxg2.jpg', 'https://www.youtube.com/watch?v=IiQxXqcQFKA', 'The City of Pittsburgh offers everything a person might be looking for in a home, with its wonderful shopping areas, great restaurants, and beautiful views. Pittsburgh is known for having more named neighborhoods than any American city. Ninety distinct communities collectively create the culture of Pittsburgh. The Strip District is a wonderful market spot known for its variety of food and an excellent shopping with unique stores. The neighborhoods known as Shadyside, Beechview, or the West End are other fantastic region of Pittsburgh to find trendy shops and restaurants. If you’re looking for authentic homemade Italian food, Bloomfield or “Pittsburgh’s Little Italy” is the place for you. If you’re looking for more of a creative and artistic side of Pittsburgh, pass through the neighborhood of Lawrenceville. East Liberty is known as for business and industries. Looking for a fun night out on the town? South Side has a fun and exciting nightlife full of clubs, sports bars, and laid-back pubs. Wanting to catch a game or spend some quality time with your loved ones? The North Shore or North Side is the place to be. Mount Washington is the high point of Pittsburgh; it gives the residents of Pittsburgh and tourists a beautiful view of this magnificent city. Finally, Downtown, like most downtown districts, has great food, dining, parks, plazas, and art museums.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Miami', 'Florida', 'https://justinkelefas.com/wp-content/uploads/2021/03/Miami-Skyline-Cityscape-Sunset-Sunrise-Downtown-January-by-Justin-Kelefas-2021-ver2.jpg', 'https://www.youtube.com/watch?v=tsyW6zFuaBk', 'Miami is one of the citys and the world’s most popular vacation spots. Though destinations often are said to offer something for everyone, the Miami area does indeed offer multiple enticements for all. The trendy nightlife of South Beach, bejeweled by the eye candy of the Art Deco district. The bustle of Calle Ocho and the highly caffeinated energy of Little Havana. The plush hotels of Miami Beach and the historic hideaways of Coral Gables. Seemingly endless shopping opportunities in modern, sprawling malls and the quiet, personal attention offered by the family-owned shops of Coconut Grove and many other corners of the region. The lures of deep-sea fishing and golf and tennis. Miamis professional football, basketball, baseball and hockey. Boat shows and auto racing. Art festivals and outdoor food and wine extravaganzas. An international airport and the world’s busiest cruise port.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('New York City', 'New York', 'https://wallpaperset.com/w/full/7/9/6/306973.jpg', 'https://www.youtube.com/watch?v=lkkyTUpyIyk', 'New York City is one of the global hubs of international finance, politics, communications, film, music, fashion, and culture. Alongside London its one of only two universally acknowledged to be "World Cities" the most important and influential cities on Earth. Its home to many world-class museums, art galleries, and theaters. Many of the worlds largest corporations have their headquarters here. The headquarters of the United Nations is in New York and most countries have a consulate here. This citys influence on the globe, and all its inhabitants, is hard to overstate, as decisions made within its boundaries often have impacts and ramifications across the world. New York City has a humid subtropical climate, experiencing all four seasons and with about 50 inches (1,200mm) of rainfall evenly distributed throughout the year. Depending on the time of the year you visit it would be optimal to know what kind of weather you should expect. The diverse population runs the gamut from some of Americas wealthiest celebrities and socialites to homeless people. There are millions of immigrants living in the city. New Yorks population has been diverse since the citys founding by the Dutch. Successive waves of immigration from virtually every nation in the world make New York a giant social experiment in cross-cultural harmony.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Seattle', 'Washington', 'https://wallpapers.com/images/featured/seattle-4k-ev7703uyplbpbk90.jpg', 'https://www.youtube.com/watch?v=Fr2_DXuPYwA', 'Seattle is a city of many neighborhoods: eclectic, urban, outdoorsy, artsy, gritty, down-to-earth, or posh—its all here, from the quirky character of the Seattle Waterfront and the eccentric "Republic of Fremont," to hipsters walking baby carriages past aging mansions on Capitol Hill. Theres something for just about everyone within this vibrant Emerald City. Seattle’s diversity lies in the topography: the city is a feat of environmental engineering. When the Denny party arrived on its shores, "Seattle" was a series of densely forested valleys covered by Douglas fir, Western hemlock, and red cedar. Where SoDo (the stadium district south of Downtown) currently it was nothing but mudflats. Pioneer Square was actually an island of sorts where Duwamish tribespeople crossed to the mainland over sandbars. Seattle’s diversity lies in the topography: the city is a feat of environmental engineering. Once Seattle started to grow, its residents literally changed the landscape. Massive Denny Hill once occupied the Belltown neighborhood, but it simply had to go. The multistage "regrade" started in 1899 and was completed 32 years later. The Denny Hill Regrade was just one of dozens of projects; another equally ambitious earth-moving mission was the construction of the canal that links Lake Washington to Puget Sound. Today, the city is once again moving a lot of earth around with the construction of a light-rail line across the city; it’s changing the look, feel, and energy of neighborhoods as a result.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Los Angeles', 'California','https://w.forfun.com/fetch/f7/f780d54ca47a0a33b4da2b5d0ac9ee52.jpeg', 'https://www.youtube.com/watch?v=VOM__NZAFQM', 'The city of Los Angeles (also known simply as L.A., and nicknamed the "City of Angels") is the most populous city in California. Located on a broad basin in Southern California, the city is surrounded by vast mountain ranges, valleys, forests, beautiful beaches along the Pacific Ocean, and nearby desert. The metropolitan area is the second most populous city in the United States and home to over 17 million people. The metropolitan area is spread across Los Angeles County, Orange County, and parts of San Bernardino County, Riverside County, and Ventura County. Los Angeles is an important center of culture, medicine, agriculture, business, finance, energy, aerospace, science, food processing, media, international trade, and tourism. Los Angeles is huge. From the Sylmar district in the north to the Port of Los Angeles in the south, the drive can be close to an hour and a half long;longer once traffic is factored in. The L.A. metropolitan area includes smaller cities, such as Santa Monica, Burbank, Pasadena, Long Beach, Anaheim, and Riverside some of which were founded around the end of the nineteenth century and retain distinct identities. Geographically, some district names in the city of Los Angeles are so common, that they are believed by some to be separate cities when in fact, they are actually neighborhoods of Los Angeles. Hollywood, Van Nuys, Encino, and Bel-Air are just some well-known examples of neighborhoods that are actually within Los Angeles and not separate entities, while West Hollywood, Santa Monica, and Beverly Hills, for instance, are actually independent cities. Los Angeles is one of the most diverse cities in the nation and thus the world in terms of its citizens’ ethnicities and economic standing. More than a third of the city’s population is foreign born, numbering just below 1.5 million. The people of Los Angeles come from all over the world and are dispersed throughout the city’s many sprawling, unique neighborhoods, though many of them congregate in ethnic enclaves like Little Armenia, Koreatown, Little Ethiopia, Chinatown, Little Tokyo, Historic Filipinotown or Tehrangeles.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Washington D.C.', '', 'https://wallpaper-mania.com/wp-content/uploads/2018/09/High_resolution_wallpaper_background_ID_77700311759.jpg', 'https://www.youtube.com/watch?v=pIxg7wFY2cM', 'Washington, D.C., has long been saddled with a reputation as a stuffy government-driven town. A "city of southern efficiency and northern charm," as John F. Kennedy once described it, Washington is often seen by outsiders as slow and inefficient. But these days, our nations capital is awash with a new energy, transforming itself into an exciting, faster-paced East Coast vacation destination. Although the government is still the sun around which this city orbits, the District also offers a host of renowned museums and interesting neighborhoods. And with a recent explosion of restaurants, cafes, boutiques and clubs, D.C. is transitioning into a thriving cultural hub. As the D.C. Tourism Board is emphasizing through its DC Cool campaign, this isnt the Washington you remember from your middle school field trip – its much hipper than that. You can choose a traditional D.C. adventure, filled with tours of classic attractions like the White House and the Washington Monument, the U.S. Capitol and the Smithsonian National Museum of Natural History. And theres no better way to experience iconic D.C. than with a stroll around the Tidal Basin. (Plan to visit in late March or early April – just in time for the National Cherry Blossom Festival – and youll be rewarded with a canopy of beautiful pink blooms.) But if youve already seen the national landmarks, get a feel for the citys more youthful ambiance, highlighted by its urban neighborhoods, marquee art galleries and vibrant farmers markets. While youll only need a few days to see the city as you know it from your history book, it could take months to experience the Washington that todays locals know and love.' );
-- BAR
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://pbs.twimg.com/media/DSjrSNaX4AA1ZNH.jpg:large', 'Mario''s South Side Saloon', 'https://mariospgh.com/southside/', 'https://mariospgh.com/southside/', '1514 E Carson St, Pittsburgh, PA 15203', '(412) 381-5610', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://www.thedustinnulfteam.com/uploads/1/1/8/0/11801389/tc28_orig.jpg', 'Tequila Cowboy', 'https://tequilacowboy.com/pittsburgh/', 'https://tequilacowboy.com/pittsburgh/', '380 N Shore Dr, Pittsburgh, PA 15212', '(412) 930-0895', '$$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://media2.fdncms.com/pittsburgh/imager/u/original/10906353/food1_walters2web_39.jpg', 'Walter''s BBQ Southern Kitchen', 'https://walterspgh.com/', 'https://walterspgh.com/', '4501 Butler St, Pittsburgh, PA 15201', '(412) 683-7474', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://www.discovertheburgh.com/wp-content/uploads/2022/11/Pins-Mechanical-2000px-01.jpg', 'Pins Mechanical Co.', 'https://www.pinsbar.com/pittsburgh', 'https://www.pinsbar.com/pittsburgh', '407 Cinema Dr, Pittsburgh, PA 15203', '(412) 365-5575', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://www.pittsburghmagazine.com/content/uploads/2022/12/l/o/shortysoverhead.jpg', 'Shorty''s Pins x Pints', 'https://www.shortysx.com/', 'https://www.shortysx.com/', '353 N Shore Dr, Pittsburgh, PA 15212', '(412) 904-4450', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (1, 'https://bloximages.chicago2.vip.townnews.com/buffalonews.com/content/tncms/assets/v3/editorial/0/cc/0cc7a238-1fc1-11ec-ab5b-dbf7eea14a8f/61520e0fd2e86.image.jpg?resize=1200%2C900', 'Southern Tier Brewery', 'https://taprooms.stbcbeer.com/southern-tier-brewery-pittsburgh-0c78961b7fd2', 'https://taprooms.stbcbeer.com/southern-tier-brewery-pittsburgh-0c78961b7fd2', '316 N Shore Dr, Pittsburgh, PA 15212', '(412) 301-2337', '$$$');

INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/95/49/0e/our-staff-performing.jpg?w=1200&h=-1&s=1', 'Mango''s Tropical Cafe', 'https://mangos.com/mangos-miami-beach/', 'https://mangos.com/mangos-miami-beach/mangos-miami-beach-menu/', '900 Ocean Drive Miami Beach, FL 33139', '(305) 673-4422', '$$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/f1/ae/88/join-us-at-sky-yard-rooftop.jpg?w=1200&h=-1&s=1', 'Rooftop Hookah Exotix - Sky Yard', 'https://www.hookahexotix.com/service-page/hookah-exotix-sky-yard-rooftop?referral=service_list_widget', 'https://www.livnightclub.com/', 'Sky Yard rooftop bar, 723 Lincoln Ln N, Miami Beach, FL', '(754) 971-9586', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/04/6b/d6/2d/story-nightclub.jpg?w=1200&h=-1&s=1', 'STORY Nightclub', 'https://www.livnightclub.com/', 'https://www.livnightclub.com/reservations', '136 Collins Ave, Miami Beach, FL 33139', '(305) 479-4426', '$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/2e/f1/a9/celebration-of-life.jpg?w=1200&h=-1&s=1', 'Nikki Beach', 'https://miami-beach.nikkibeach.com/', 'https://miami-beach.nikkibeach.com/', 'One Ocean Drive Miami Beach FL, 33139', '(305) 538-1111', '$$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/09/33/45/d5/bleau-bar.jpg?w=1200&h=-1&s=1', 'Bleau Bar', 'https://www.fontainebleau.com/nightlife/bars/miami-beach-hotel-bars', 'https://www.fontainebleau.com/nightlife/bars/miami-beach-hotel-bars', '4441 Collins Avenue Miami Beach FL. 33140', '(305) 674-4760', '$$$$');
INSERT INTO bar (bar_city_id, bar_picture, bar_name, bar_website, menu_link, bar_address, bar_phone, bar_price) VALUES (2, 'https://bloximages.chicago2.vip.townnews.com/buffalonews.com/content/tncms/assets/v3/editorial/0/cc/0cc7a238-1fc1-11ec-ab5b-dbf7eea14a8f/61520e0fd2e86.image.jpg?resize=1200%2C900', 'Sugar', 'https://www.easthotels.com/en/miami/restaurants-and-bars/sugar/', 'https://www.easthotels.com/en/miami/restaurants-and-bars/sugar/', '788 Brickell Plz East Miami, Miami, FL 33131-3521', ' (786)805-4655', '$$$$');
-- RESTAURANT
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://lh3.googleusercontent.com/p/AF1QipO2Ec6bas-f-HgUE7VDFPLgjFV9XKbEQWeilnmJ=s680-w680-h510', 'The Capital Grille', 'https://www.thecapitalgrille.com/reservations', '301 5th Ave, Pittsburgh, PA 15222', ' (412) 338-9100', '$$$$');
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://www.pittsburghmagazine.com/content/uploads/2022/04/j/k/gauchoroom.jpg', 'Gaucho Parrilla Argentina', 'https://www.eatgaucho.com/menu', '146 6th St, Pittsburgh, PA 15222, Pittsburgh, PA 15222', '(412)709-6622', '$$$');
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://www.visitpittsburgh.com/imager/files_idssasp_com/public/C32/0603d240-7bb4-4f26-8659-cfb449095dc1/1f223bed-7d76-46f3-865c-85c34e257fe0_2200f99a241e55606b0e5da9ea141a39.jpg', 'Primanti Bros.', 'https://locations.primantibros.com/pa/pittsburgh/', '46 18th St, Pittsburgh, PA 15222', '(412) 263-2142', '$');
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://carrieholstead.com/wp-content/uploads/2020/03/Picture_JK_Statement-Bldg-lobby-03-09-20-960x720_c.jpg', 'The Speckled Egg', 'https://thespeckledeggpgh.com', '501 Grant St, Pittsburgh, PA 15219', '(412) 251-5248', '$$');
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://www.sandandorsnow.com/wp-content/uploads/2018/05/fogo-entrance-2.jpg', 'Fogo de Chão', 'https://fogodechao.com/location/pittsburgh/', '525 Smithfield St, Pittsburgh, PA 15222', '(412) 312-5001', '$$$');
INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (1, 'https://lh3.googleusercontent.com/p/AF1QipNxqfGjzmEDMWeg9pYXtMW8tFeAQhEegIpD6ZH1=s680-w680-h510', 'The Melting Pot', 'https://www.meltingpot.com/pittsburgh-pa/', '242 W Station Square Dr, Pittsburgh, PA 15219', '(412) 261-3477', '$$$');

-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (2, '', '', '', '', '', '', '');

-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (3, '', '', '', '', '', '', '');

-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (4, '', '', '', '', '', '', '');

-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (5, '', '', '', '', '', '', '');

-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- INSERT INTO restaurant (restaurant_city_id, restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price) VALUES (6, '', '', '', '', '', '', '');
-- HOTEL
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0d/f0/4e/c6/street--v14917814.jpg?w=1200&h=-1&s=1', 'Pittsburgh', 'The Priory Hotel', 'https://direct-book.com/properties/2308theprioryhoteldirect?locale=en&items[0][adults]=2&items[0][children]=0&items[0][infants]=0&currency=USD&checkInDate=2023-08-28&checkOutDate=2023-08-29&trackPage=yes', '614 Pressley St, Pittsburgh, PA 15212-5616', '(412) 960-8278', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://cms.druryhotels.com/media/467707/172-exterior.png?anchor=center&mode=crop&width=659&height=422&rnd=133307098360000000', 'Pittsburgh', 'Drury Plaza Hotel', 'https://www.druryhotels.com/bookandstay/newreservation', '745 Grant St, Pittsburgh, PA 15219-2213', '(855) 943-6025', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0f/6e/6a/64/hampton-inn-suites-pittsburgh.jpg?w=1200&h=-1&s=1', 'Pittsburgh', 'Hampton Inn & Suites', 'https://www.hilton.com/en/hotels/pitdnhx-hampton-suites-pittsburgh-downtown/rooms/', '1247 Smallman St, Pittsburgh, PA 15222-4239', '(855) 605-0317', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/23/b2/4e/2c/hotel-exterior.jpg?w=1100&h=-1&s=1', 'Pittsburgh', 'Cambria Hotel', 'https://www.choicehotels.com/cambria/promo/cambria-hotel-discount?ratePlanCode=SC20M3', '1320 Centre Ave, Pittsburgh, PA 15219-3505', '(844) 426-3619', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://www.ahstatic.com/photos/a571_ho_00_p_1024x768.jpg', 'Fairmont Pittsburgh', 'https://www.fairmont.com/pittsburgh/', 'Pittsburgh', '510 Market Street, Pittsburgh, PA 15222, United States', ' (412) 773-8800', '$$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture,hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (1, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/26/80/0b/77/exterior.jpg?w=1100&h=-1&s=1', 'Pittsburgh', 'Joinery Hotel', 'https://www.hilton.com/en/hotels/pitktqq-joinery-hotel-pittsburgh/?SEO_id=GMB-AMER-QQ-PITKTQQ&y_source=1_OTU3NDUxNC03MTUtbG9jYXRpb24ud2Vic2l0ZQ%3D%3D', '453 Boulevard Of The Allies, Pittsburgh, PA 15219', '(844) 442-8746', '$$$');

INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://res.cloudinary.com/traveltripperweb/image/upload/c_limit,f_auto,h_2500,q_auto,w_2500/v1690366649/i3jhizj78lsbgt7ydjah.jpg', 'Miami', 'The Elser Hotel & Residences', 'https://www.theelserhotel.com/', '398 NE 5th Street, Miami, FL 33132', '(786) 472-1000', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0e/3a/55/61/hotel-exterior.jpg?w=1200&h=-1&s=1', 'Miami', 'Hampton Inn & Suites Miami', 'http://www.hamptoninnmiamibrickell.com/', '50 SW 12th Street, Miami, FL 33130', '(855) 605-0317', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/c8/06/86/rooftop-pool-views.jpg?w=1200&h=-1&s=1', 'Miami', 'Novotel Miami Brickell', 'https://www.novotelmiami.com/', '1500 SW 1st Ave, Miami, FL 33129-1115', '(786) 627-5119', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/28/b4/10/hotel-exterior.jpg?w=1200&h=-1&s=1', 'Miami', 'InterContinental Miami', 'https://www.ihg.com/intercontinental/hotels/us/en/miami/miaha/hoteldetail?fromRedirect=true&qSrt=sAV&qIta=99618083&icdv=99618083&glat=MEDI_TABA-website_mdpr_TripAdvisorUS_BLwebsiteD_MIAHA_us_en_ba&qSlH=MIAHA&qRms=1&qAdlt=2&qChld=0&qCiD=10&qCiMy=082023&qCoD=11&qCoMy=082023&setPMCookies=true&qSHBrC=IC&qDest=100%2520Chopin%2520Plaza%252C%2520Miami%252C%2520FL%252C%2520US&cm_mmc=TABA-website_mdpr_TripAdvisorUS_BLwebsiteD_MIAHA_us_en_ba&srb_u=1', '100 Chopin Plaza, Miami, FL 33131', '(877) 640-6822', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/45/35/d6/hotel-aka-brickell.jpg?w=1200&h=-1&s=1', 'Miami', 'Hotel AKA Brickell', 'https://www.stayaka.com/hotel-aka-brickell?utm_source=tripadvisor.com_bl&utm_medium=media&utm_content=homepagelink&utm_campaign=tripadvisor-bl-omshs-36146', '1395 Brickell Ave, Miami, FL 33131-3353', ' (305) 503-6500', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/27/b9/c6/86/miccosukee-casino-resort.jpg?w=800&h=-1&s=1', 'Miami', 'Miccosukee Casino & Resort', 'https://miccosukee.com/', '500 SW 177 Ave., Miami, FL 33194-2800', '(305) 930-7955', '$$$');

INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/26/83/c9/f0/exterior.jpg?w=1100&h=-1&s=1', 'New York City', 'Motto by Hilton New York City Chelsea', 'https://www.hilton.com/en/hotels/nycdlua-motto-new-york-city-chelsea/?SEO_id=GMB-AMER-UA-NYCDLUA&y_source=1_Mjc3MjkzNzctNzE1LWxvY2F0aW9uLndlYnNpdGU%3D', '113 West 24th Street, New York City, NY 10001', '(844) 840-8277', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/28/38/de/a8/ground-floor-lobby.jpg?w=1200&h=-1&s=1', 'New York City', 'Margaritaville Resort Times Square', 'https://www.margaritavilleresorts.com/margaritaville-resort-times-square?utm_source=Tripadvisor&utm_medium=bizadv', '560 7th Ave, New York City, NY 10018', '(212) 221-3007', '$$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/36/e7/ff/magic-hour-rooftop-bar.jpg?w=1200&h=-1&s=1', 'New York City', 'Moxy NYC Times Square', 'https://www.marriott.com/en-us/hotels/nycox-moxy-nyc-times-square/overview/?scid=f2ae0541-1279-4f24-b197-a979c79310b0', '485 7th Avenue, New York City, NY 10018', '(844) 631-0595', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/ab/7a/a2/arlo-soho.jpg?w=1200&h=-1&s=1', 'New York City', 'Arlo SoHo', 'https://arlohotels.com/soho/', '231 Hudson St, New York City, NY 10013', '(212) 342-7000', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/28/91/d4/53/pod-times-square-check.jpg?w=1100&h=-1&s=1', 'New York City', 'Pod Times Square', 'https://www.thepodhotel.com/pod-times-square?utm_source=google&utm_medium=organic&utm_campaign=gbp_listing', '400 West 42nd Street, New York City, NY 10036-6809', '(844) 763-7666', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/03/61/e8/3a/the-bryant-park-hotel.jpg?w=1200&h=-1&s=1', 'New York City', 'The Bryant Park Hotel', 'https://bryantparkhotel.com/#utm_source=TripAdvisor&utm_medium=Website', '40 West 40th Street, New York City, NY 10018-2602', '(646) 759-1610', '$$$$$');

INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/08/4a/ff/amenities.jpg?w=1200&h=-1&s=1', 'Seattle', 'Staypineapple, The Maxwell Hotel, Seattle Center', 'https://www.staypineapple.com/the-maxwell-hotel-seattle-wa?utm_source=google&utm_medium=organic&utm_campaign=GMB&utm_term=tmh', '300 Roy Street, Seattle, WA 98109', '(206) 286-0629', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/26/37/5f/92/exterior.jpg?w=1100&h=-1&s=1', 'Seattle', 'Embassy Suites by Hilton Seattle Downtown', 'https://www.hilton.com/en/hotels/seapses-embassy-suites-seattle-downtown-pioneer-square/?SEO_id=GMB-AMER-ES-SEAPSES&y_source=1_OTA1MDk1NC03MTUtbG9jYXRpb24ud2Vic2l0ZQ%3D%3D', '255 South King Street, Seattle, WA 98104', '(855) 605-0319', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/24/ff/4c/5c/exterior.jpg?w=1100&h=-1&s=1', 'Seattle', 'Hyatt Regency Seattle', 'https://www.hyatt.com/en-US/hotel/washington/hyatt-regency-seattle/sears?src=prop_tripadvisor_businesslisting_sears_en_offers_desktop_main_homepage', '808 Howell Street, Seattle, WA 98101-1384', '(206) 973-1234', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0f/90/de/7d/entrance.jpg?w=1200&h=-1&s=1', 'Seattle', 'Belltown Inn', 'https://www.belltown-inn.com/', '2301 3rd Ave, Seattle, WA 98121-1711', '(206) 529-3700', '$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/7c/e0/96/inn-at-the-market.jpg?w=1200&h=-1&s=1', 'Seattle', 'Inn at the Market', 'https://www.innatthemarket.com/', '86 Pine St., Seattle, WA 98101-1571', '(206) 687-1440', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (4, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/51/f0/85/lotte-hotel-exterior.jpg?w=1100&h=-1&s=1', 'Seattle', 'Lotte Hotel Seattle', 'lottehotelseattle.com', '809 5th Ave 16th floor, Seattle, WA 98104-1663', '(206) 589-8270', '$$$$');

INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/63/0f/a5/corner-double-queen-city.jpg?w=1200&h=-1&s=1', 'Los Angeles', 'Moxy Downtown Los Angeles', 'https://www.marriott.com/en-us/hotels/laxox-moxy-downtown-los-angeles/overview/', '1260 South Figueroa Street, Los Angeles, CA 90015', '(888) 597-4165', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/18/c4/15/pool-area.jpg?w=1200&h=-1&s=1', 'Los Angeles', 'Sheraton Universal Hotel', 'https://www.marriott.com/en-us/hotels/bursi-sheraton-universal-hotel/overview/?scid=f2ae0541-1279-4f24-b197-a979c79310b0', '333 Universal Hollywood Drive, Los Angeles, CA 91608', '(844) 631-0595', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/10/34/f2/hotel-exterior.jpg?w=1200&h=-1&s=1', 'Los Angeles', 'The Biltmore Los Angeles', 'https://www.millenniumhotels.com/en/los-angeles/millennium-biltmore-hotel-los-angeles/', '506 S. Grand Ave, Los Angeles, CA 90071-2602', '(213) 624-1011', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/df/5e/1d/driving-towards-the-front.jpg?w=1100&h=-1&s=1', 'Los Angeles', 'Hilton Los Angeles / Universal City', 'https://www.hilton.com/en/hotels/buruchf-hilton-los-angeles-universal-city/?SEO_id=GMB-AMER-HH-BURUCHF&y_source=1_MTIyMDkyOC03MTUtbG9jYXRpb24ud2Vic2l0ZQ%3D%3D', '555 Universal Hollywood Drive, Los Angeles, CA 91608-1001', '(855) 605-0316', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/09/6e/bc/80/cabana-with-balcony--v9307655.jpg?w=1200&h=-1&s=1', 'Los Angeles', 'The Hollywood Roosevelt', 'https://www.thehollywoodroosevelt.com/?utm_source=google&utm_medium=organic&utm_campaign=gbp_listing', '7000 Hollywood Boulevard, Los Angeles, CA 90028-6003', '(323) 856-1970', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (5, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/b4/18/dd/holiday-inn-express-hollywood.jpg?w=1200&h=-1&s=1', 'Los Angeles', 'Holiday Inn Express & Suites Hollywood Walk of Fame', 'https://www.ihg.com/holidayinnexpress/hotels/us/en/los-angeles/laxfa/hoteldetail?fromRedirect=true&qSrt=sAV&qIta=99618083&icdv=99618083&glat=MEDI_TABA-website_mdpr_TripAdvisorUS_BLwebsiteD_LAXFA_us_en_ba&qSlH=LAXFA&qRms=1&qAdlt=2&qChld=0&qCiD=10&qCiMy=082023&qCoD=11&qCoMy=082023&setPMCookies=true&qSHBrC=EX&qDest=1921%2520N.%2520Highland%2520Avenue%252C%2520Los%2520Angeles%252C%2520CA%252C%2520US&cm_mmc=TABA-website_mdpr_TripAdvisorUS_BLwebsiteD_LAXFA_us_en_ba&srb_u=1', '1921 North Highland Avenue, Los Angeles, CA 90068', '(877) 819-8632', '$$$');

INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/06/5b/0f/hotel-hive.jpg?w=1200&h=-1&s=1', 'Washington D.C.', 'Hotel Hive', 'https://hotelhive.com/?utm_source=tripadvisor_businesslisting&utm_medium=hotelwebsite', '2224 F Street NW, Washington DC, DC 20037', '(202) 849-8499', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/e1/06/8a/lyle-washington-dc.jpg?w=1200&h=-1&s=1', 'Washington D.C.', 'Lyle Washington DC', 'https://www.lyledc.com/?utm_source=Tripadvisor&utm_medium=BusinessAdv', '1731 New Hampshire Ave NW, Washington DC, DC 20009-2501', '(202) 963-7768', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/50/bc/fa/exterior.jpg?w=1200&h=-1&s=1', 'Washington D.C.', 'Hamilton Hotel', 'https://www.hamiltonhoteldc.com/?utm_source=google%20my%20business&utm_medium=listing&utm_campaign=visit%20website', '1001 14th St NW, Washington DC, DC 20005', '(202) 682-0111', '$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/23/e3/97/67/hotel-exterior.jpg?w=1100&h=-1&s=1', 'Washington D.C.', 'Washington Plaza Hotel', 'https://www.washingtonplazahotel.com/?utm_source=google%20my%20business&utm_medium=listing&utm_campaign=visit%20website', '10 Thomas Circle NW, Washington DC, DC 20005', '(202) 842-1300', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/27/e6/c1/d0/exterior.jpg?w=1100&h=-1&s=1', 'Washington D.C.', 'The Royal Sonesta Washington DC Dupont Circle', 'https://www.sonesta.com/royal-sonesta/dc/washington/royal-sonesta-washington-dc-dupont-circle?utm_campaign=business_advantage&utm_medium=listing&utm_source=tripadvisor&src=tripadvisor_business_advantage', '2121 P Street NW, Washington DC, DC 20037', '(202) 410-9320', '$$$');
INSERT INTO hotel (hotel_city_id, hotel_picture, hotel_city_name, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price) VALUES (6, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/29/39/a3/0b/exterior.jpg?w=1100&h=-1&s=1', 'Washington D.C.', 'Hilton Washington DC National Mall The Wharf', 'https://www.hilton.com/en/hotels/dcaephh-hilton-washington-dc-national-mall-the-wharf/?SEO_id=GMB-AMER-HH-DCAEPHH&y_source=1_MTIzOTE2NTMtNzE1LWxvY2F0aW9uLndlYnNpdGU%3D', '480 L''Enfant Plaza SW, Washington DC, DC 20024', '(855) 605-0316', '$$');


COMMIT;