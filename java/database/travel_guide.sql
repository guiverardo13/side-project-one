BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, city, city_likes, account, user_account, user_likes, like_categories, hotel, restaurant, bar, events, tourism;

CREATE TABLE users (
	user_id serial NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(50),
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

CREATE TABLE account (
	account_id serial NOT NULL,
	CONSTRAINT pk_account PRIMARY KEY (account_id)
);

CREATE TABLE user_account (
	account_id int NOT NULL REFERENCES account(account_id),
	user_id int NOT NULL,
	CONSTRAINT pk_user_account PRIMARY KEY (user_id, account_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE like_categories (
	like_id serial NOT NULL,
	like_name varchar(50) NOT NULL,
	CONSTRAINT pk_like_categories PRIMARY KEY (like_id)
);

CREATE TABLE user_likes (
	user_id int NOT NULL REFERENCES users(user_id),
	like_id int NOT NULL,
	CONSTRAINT pk_user_likes PRIMARY KEY (user_id, like_id),
	CONSTRAINT fk_user_like_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_user_like_like FOREIGN KEY (like_id) REFERENCES like_categories(like_id)
);

CREATE TABLE hotel (
	hotel_id serial NOT NULL,
	hotel_city_id INT NOT NULL,
	hotel_name varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	hotel_address varchar(50) NOT NULL,
	hotel_phone varchar(12) NOT NULL,
	hotel_price varchar(5) NOT NULL,
	CONSTRAINT pk_hotel PRIMARY KEY (hotel_id),
	CONSTRAINT fk_hotel_city FOREIGN KEY(hotel_city_id) REFERENCES city(city_id)
);

CREATE TABLE restaurant (
	restaurant_id serial NOT NULL,
	restaurant_city_id INT NOT NULL,
	restaurant_name varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	restaurant_address varchar(50) NOT NULL,
	restaurant_phone varchar(12) NOT NULL,
	restaurant_price varchar(5) NOT NULL,
	CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id),
	CONSTRAINT fk_restaurant_city FOREIGN KEY(restaurant_city_id) REFERENCES city(city_id)
);

CREATE TABLE bar (
	bar_id serial NOT NULL,
	bar_city_id INT NOT NULL,
	bar_name varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	bar_address varchar(50) NOT NULL,
	bar_phone varchar(12) NOT NULL,
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

INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Pittsburgh', 'Pennsylvania','https://cdn.wallpapersafari.com/34/60/pwHxg2.jpg', 'https://www.youtube.com/watch?v=IiQxXqcQFKA', 'The City of Pittsburgh offers everything a person might be looking for in a home, with its wonderful shopping areas, great restaurants, and beautiful views. Pittsburgh is known for having more named neighborhoods than any American city. Ninety distinct communities collectively create the culture of Pittsburgh. The Strip District is a wonderful market spot known for its variety of food and an excellent shopping with unique stores. The neighborhoods known as Shadyside, Beechview, or the West End are other fantastic region of Pittsburgh to find trendy shops and restaurants. If you’re looking for authentic homemade Italian food, Bloomfield or “Pittsburgh’s Little Italy” is the place for you. If you’re looking for more of a creative and artistic side of Pittsburgh, pass through the neighborhood of Lawrenceville. East Liberty is known as for business and industries. Looking for a fun night out on the town? South Side has a fun and exciting nightlife full of clubs, sports bars, and laid-back pubs. Wanting to catch a game or spend some quality time with your loved ones? The North Shore or North Side is the place to be. Mount Washington is the high point of Pittsburgh; it gives the residents of Pittsburgh and tourists a beautiful view of this magnificent city. Finally, Downtown, like most downtown districts, has great food, dining, parks, plazas, and art museums.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Miami', 'Florida', 'https://justinkelefas.com/wp-content/uploads/2021/03/Miami-Skyline-Cityscape-Sunset-Sunrise-Downtown-January-by-Justin-Kelefas-2021-ver2.jpg', 'https://www.youtube.com/watch?v=tsyW6zFuaBk', 'Miami is one of the citys and the world’s most popular vacation spots. Though destinations often are said to offer something for everyone, the Miami area does indeed offer multiple enticements for all. The trendy nightlife of South Beach, bejeweled by the eye candy of the Art Deco district. The bustle of Calle Ocho and the highly caffeinated energy of Little Havana. The plush hotels of Miami Beach and the historic hideaways of Coral Gables. Seemingly endless shopping opportunities in modern, sprawling malls and the quiet, personal attention offered by the family-owned shops of Coconut Grove and many other corners of the region. The lures of deep-sea fishing and golf and tennis. Miamis professional football, basketball, baseball and hockey. Boat shows and auto racing. Art festivals and outdoor food and wine extravaganzas. An international airport and the world’s busiest cruise port.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('New York City', 'New York', 'https://wallpaperset.com/w/full/7/9/6/306973.jpg', 'https://www.youtube.com/watch?v=lkkyTUpyIyk', 'New York City is one of the global hubs of international finance, politics, communications, film, music, fashion, and culture. Alongside London its one of only two universally acknowledged to be "World Cities" the most important and influential cities on Earth. Its home to many world-class museums, art galleries, and theaters. Many of the worlds largest corporations have their headquarters here. The headquarters of the United Nations is in New York and most countries have a consulate here. This citys influence on the globe, and all its inhabitants, is hard to overstate, as decisions made within its boundaries often have impacts and ramifications across the world. New York City has a humid subtropical climate, experiencing all four seasons and with about 50 inches (1,200mm) of rainfall evenly distributed throughout the year. Depending on the time of the year you visit it would be optimal to know what kind of weather you should expect. The diverse population runs the gamut from some of Americas wealthiest celebrities and socialites to homeless people. There are millions of immigrants living in the city. New Yorks population has been diverse since the citys founding by the Dutch. Successive waves of immigration from virtually every nation in the world make New York a giant social experiment in cross-cultural harmony.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Seattle', 'Washington', 'https://wallpapers.com/images/featured/seattle-4k-ev7703uyplbpbk90.jpg', 'https://www.youtube.com/watch?v=Fr2_DXuPYwA', 'Seattle is a city of many neighborhoods: eclectic, urban, outdoorsy, artsy, gritty, down-to-earth, or posh—its all here, from the quirky character of the Seattle Waterfront and the eccentric "Republic of Fremont," to hipsters walking baby carriages past aging mansions on Capitol Hill. Theres something for just about everyone within this vibrant Emerald City. Seattle’s diversity lies in the topography: the city is a feat of environmental engineering. When the Denny party arrived on its shores, "Seattle" was a series of densely forested valleys covered by Douglas fir, Western hemlock, and red cedar. Where SoDo (the stadium district south of Downtown) currently it was nothing but mudflats. Pioneer Square was actually an island of sorts where Duwamish tribespeople crossed to the mainland over sandbars. Seattle’s diversity lies in the topography: the city is a feat of environmental engineering. Once Seattle started to grow, its residents literally changed the landscape. Massive Denny Hill once occupied the Belltown neighborhood, but it simply had to go. The multistage "regrade" started in 1899 and was completed 32 years later. The Denny Hill Regrade was just one of dozens of projects; another equally ambitious earth-moving mission was the construction of the canal that links Lake Washington to Puget Sound. Today, the city is once again moving a lot of earth around with the construction of a light-rail line across the city; it’s changing the look, feel, and energy of neighborhoods as a result.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Los Angeles', 'California','https://w.forfun.com/fetch/f7/f780d54ca47a0a33b4da2b5d0ac9ee52.jpeg', 'https://www.youtube.com/watch?v=VOM__NZAFQM', 'The city of Los Angeles (also known simply as L.A., and nicknamed the "City of Angels") is the most populous city in California. Located on a broad basin in Southern California, the city is surrounded by vast mountain ranges, valleys, forests, beautiful beaches along the Pacific Ocean, and nearby desert. The metropolitan area is the second most populous city in the United States and home to over 17 million people. The metropolitan area is spread across Los Angeles County, Orange County, and parts of San Bernardino County, Riverside County, and Ventura County. Los Angeles is an important center of culture, medicine, agriculture, business, finance, energy, aerospace, science, food processing, media, international trade, and tourism. Los Angeles is huge. From the Sylmar district in the north to the Port of Los Angeles in the south, the drive can be close to an hour and a half long;longer once traffic is factored in. The L.A. metropolitan area includes smaller cities, such as Santa Monica, Burbank, Pasadena, Long Beach, Anaheim, and Riverside some of which were founded around the end of the nineteenth century and retain distinct identities. Geographically, some district names in the city of Los Angeles are so common, that they are believed by some to be separate cities when in fact, they are actually neighborhoods of Los Angeles. Hollywood, Van Nuys, Encino, and Bel-Air are just some well-known examples of neighborhoods that are actually within Los Angeles and not separate entities, while West Hollywood, Santa Monica, and Beverly Hills, for instance, are actually independent cities. Los Angeles is one of the most diverse cities in the nation and thus the world in terms of its citizens’ ethnicities and economic standing. More than a third of the city’s population is foreign born, numbering just below 1.5 million. The people of Los Angeles come from all over the world and are dispersed throughout the city’s many sprawling, unique neighborhoods, though many of them congregate in ethnic enclaves like Little Armenia, Koreatown, Little Ethiopia, Chinatown, Little Tokyo, Historic Filipinotown or Tehrangeles.');
INSERT INTO city (city_name, state_name, cover_picture, city_video, city_description) VALUES ('Washington D.C.', '', 'https://wallpaper-mania.com/wp-content/uploads/2018/09/High_resolution_wallpaper_background_ID_77700311759.jpg', 'https://www.youtube.com/watch?v=pIxg7wFY2cM', 'Washington, D.C., has long been saddled with a reputation as a stuffy government-driven town. A "city of southern efficiency and northern charm," as John F. Kennedy once described it, Washington is often seen by outsiders as slow and inefficient. But these days, our nation's capital is awash with a new energy, transforming itself into an exciting, faster-paced East Coast vacation destination. Although the government is still the sun around which this city orbits, the District also offers a host of renowned museums and interesting neighborhoods. And with a recent explosion of restaurants, cafes, boutiques and clubs, D.C. is transitioning into a thriving cultural hub. As the D.C. Tourism Board is emphasizing through its DC Cool campaign, this isn't the Washington you remember from your middle school field trip – it's much hipper than that. You can choose a traditional D.C. adventure, filled with tours of classic attractions like the White House and the Washington Monument, the U.S. Capitol and the Smithsonian National Museum of Natural History. And there's no better way to experience iconic D.C. than with a stroll around the Tidal Basin. (Plan to visit in late March or early April – just in time for the National Cherry Blossom Festival – and you'll be rewarded with a canopy of beautiful pink blooms.) But if you've already seen the national landmarks, get a feel for the city's more youthful ambiance, highlighted by its urban neighborhoods, marquee art galleries and vibrant farmers markets. While you'll only need a few days to see the city as you know it from your history book, it could take months to experience the Washington that today's locals know and love. );

'

INSERT INTO bar (bar_city_id, bar_name, description, bar_address, bar_phone, bar_price) VALUES (1, 'City View Bar', 'Enjoy drinks with a stunning city view', '789 Oak St', '555-123-4567', '$$$');
INSERT INTO bar (bar_city_id, bar_name, description, bar_address, bar_phone, bar_price) VALUES (1, 'Cozy Corner Pub', 'A neighborhood pub with a cozy atmosphere', '456 Maple Ave', '777-888-9999', '$$');
INSERT INTO bar (bar_city_id, bar_name, description, bar_address, bar_phone, bar_price) VALUES (1, 'Rooftop Lounge', 'Sip cocktails while overlooking the city skyline', '123 Skyview Blvd', '123-456-7890', '$$$');
INSERT INTO bar (bar_city_id, bar_name, description, bar_address, bar_phone, bar_price) VALUES (1, 'Sports Bar & Grill', 'Watch the big game while enjoying pub fare', '789 Stadium Rd', '555-555-5555', '$$');
INSERT INTO bar (bar_city_id, bar_name, description, bar_address, bar_phone, bar_price) VALUES (1, 'Jazz & Blues Club', 'Live music and soulful vibes', '321 Melody Lane', '666-777-8888', '$$');

COMMIT;

END TRANSACTION;
