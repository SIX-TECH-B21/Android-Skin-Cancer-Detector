package com.bangkit.myproject.diseaselist

class DiseaseStateData {

    val shrtNameList = ArrayList<String>()
    val lngNameList = ArrayList<String>()
    val disDesc = ArrayList<String>()
    val disSympt = ArrayList<String>()

    fun getDataPos(pos: Int): ArrayList<String>{

        val dataOut = ArrayList<String>()

        dataOut.add(shrtNameList.get(pos))
        dataOut.add(lngNameList.get(pos))
        dataOut.add(disDesc.get(pos))
        dataOut.add(disSympt.get(pos))

        return dataOut

    }

    fun getLength(): Int{
        return shrtNameList.size
    }

    init {

//        add short names
        shrtNameList.add("akiec")
        shrtNameList.add("bcc")
        shrtNameList.add("bkl")
        shrtNameList.add("df")
        shrtNameList.add("mel")
        shrtNameList.add("nv")
        shrtNameList.add("vasc")

//        add long names
        lngNameList.add("Actinic Keratoses")
        lngNameList.add("Basal Cell Carcinoma")
        lngNameList.add("Benign Keratosis-like Lesions")
        lngNameList.add("Dermatofibroma")
        lngNameList.add("Melanoma")
        lngNameList.add("Melanocytic Nevi")
        lngNameList.add("Vascular Lesions")

//        add disease description
        disDesc.add("Actinic keratosis atau solar keratosis adalah kondisi adanya tambalan kasar dan bersisik pada kulit yang berkembang akibat paparan sinar matahari bertahun-tahun. Ini sering ditemukan di wajah, bibir, telinga, lengan, kulit kepala, leher atau punggung tangan")
        disDesc.add("Karsinoma Sel Basal merupakan penyakit kanker kulit yang terbanyak dijumpai. Karsinoma sel merupakan suatu tumor kulit yang bersifat ganas, berasal dari sel-sel basal epidermis. Tumot ini berkembang lambat dan jarang bermetastasis. Keganasan pada karsinoma sel basal umumnya tidak menyebabkan kematian.")
        disDesc.add("Keratosis seboroik adalah salah satu jenis penyakit kulit, yaitu tumbuhnya benjolan seperti kutil pada permukaan kulit. Benjolan-benjolan keratosis seboroik bisa tumbuh di mana saja, kecuali pada telapak tangan, telapak kaki, atau membran mukosa (lapisan seperti di dalam mulut atau hidung). Bagian tubuh yang sering menjadi lokasi kemunculan benjolan ini adalah wajah, dada, bahu, serta punggung.")
        disDesc.add("Dermatofibroma adalah suatu kondisi kulit yang tampak seperti pertumbuhan jaringan yang berukuran kecil dan bulat. Kulit memiliki lapisan yang berbeda, diantaranya sel-sel lemak subkutan, dermis, dan epidermis. Dermatofit adalah pertumbuhan jaringan yang terjadi akibat sel pada lapisan kedua kulit (dermis) tumbuh secara tidak normal.")
        disDesc.add("Kanker kulit melanoma adalah jenis kanker yang berkembang pada melanosit, sel pigmen kulit yang berfungsi sebagai penghasil melanin. Melanin inilah yang berfungsi menyerap sinar ultraviolet dan melindungi kulit dari kerusakan. Melanoma adalah jenis kanker kulit yang jarang dan sangat berbahaya. Kondisi ini dimulai dari kulit manusia dan bisa menyebar ke organ lain dalam tubuh. Kemunculan tahi lalat baru atau perubahan pada tahi lalat yang sudah ada biasanya menjadi pertanda umum atau gejala melanoma")
        disDesc.add("Nevus melanosit adalah lesi hiperpigmentasi yang berasal dari melanosit, sel yang bertanggung jawab untuk sekresi pigmen melanin . Lebih tepatnya, nevus melanositik terbentuk mengikuti proliferasi yang tinggi - meskipun jinak - dari sel-sel tersebut. Dengan kata lain, jenis nevus ini - didefinisikan dalam bahasa umum hanya sebagai \" neo \" - mewakili lesi kulit berpigmen dari jenis melanositik dan bersifat jinak.")
        disDesc.add("Vascular lesions adalah suatu abnormalitas pada bagian kulit dan jaringan dibawahnya yang cukup umum, lebih sering dikenal sebagai tanda lahir. Terdapat tiga kategori dari vascular lessions yaitu: Hemangiomas, Vascular Malformations, dan Pyogenic Granulomas. Meski tipe-tipe ini terlihat mirip, namun setiap tipe memiliki penyebab dan pengobatan yang berbeda.")

//        add disease symptoms
        disSympt.add("- Lesi dimulai sebagai bintik-bintik kecil dan kasar yang lebih mudah dirasakan daripada yang terlihat dan yang memiliki apa yang sering digambarkan sebagai tekstur seperti amplas\n" +
                "\n" +
                "- Seiring waktu, lesi membesar, biasanya menjadi merah dan bersisik\n" +
                "\n" +
                "- Sebagian besar lesi hanya 3-10 mm, tetapi bisa juga lebih besar")
        disSympt.add("Pada karsinoma sel basal stadium dini dapat ditemukan papul, atau benjolan-benjolan putih dengan permukaan yang mengkilap seperti lilin, atau kemerahan dan terdapat telangiektasis (pelebaran pembuluh darah kapiler yang menetap pada kulit). \n" +
                "\n" +
                "Seiring waktu, benjolan tersebut bisa mengeluarkan darah dan menghasilkan kerak. Gejala karsinoma sel basal juga dapat berupa bercak-bercak bersisik berwarna cokelat atau hitam yang seiring waktu bisa membesar. \n" +
                "\n" +
                "Gejala lainnya adalah benjolan datar berwarna seperti kulit yang membesar seiring waktu. Karsinoma sel basal ini paling sering tumbuh pada daerah kepala dan leher, tapi tipe karsinoma sel basal tertentu dapat tumbuh pada daerah badan dan punggung.")
        disSympt.add("- Umumnya berwarna sawo matang, cokelat, cokelat gelap hingga kehitaman.\n" +
                "- Berbentuk bulat atau lonjong (oval).\n" +
                "- Memiliki permukaan yang kasar seperti kutil.\n" +
                "- Permukaan benjolan terlihat seperti berminyak atau berlilin.\n" +
                "- Benjolan memiliki permukaan yang rata, namun lebih menonjol dibandingkan permukaan kulit di sekitarnya.\n" +
                "- Benjolan seringkali muncul secara berkelompok.\n" +
                "- Tidak menimbulkan rasa nyeri namun dapat terasa gatal.\n")
        disSympt.add("Bentuk - benjolan bundar yang sebagian besar tumbuh dari bawah kulit.\n" +
                "Ukuran - Ukuran normal dermatofibroma berkisar dari sebesar ujung bolpoin hingga kacang, dan biasanya ukuran tidak bertambah besar.\n" +
                "Warna - Dermatofibroma dapat berwarna merah muda, merah, abu-abu, coklat muda atau ungu dalam berbagai derajat, dan dapat berubah seiring waktu.\n" +
                "Lokasi - Dermatofibroma paling sering muncul di kaki, tetapi kadang-kadang di lengan, badan. Selain tiga lokasi tersebut, dermatofibroma jarang ditemukan di bagian lain tubuh.\n" +
                "Gejala tambahan - Dermatofibroma biasanya tidak menimbulkan nyeri atau gejala apapun yang mengganggu, tetapi kadang-kadang dermatofibroma dapat menimbulkan gejala seperti gatal, nyeri tekan, nyeri, atau meradang.")
        disSympt.add("Dokter bisa mendiagnosis melanoma setelah melakukan pemeriksaan fisik. Dokter akan merujuk Anda untuk menemui dokter spesialis kulit (dermatologi) atau dokter bedah plastik jika mereka mengira Anda menderita melanoma. Jadi jika Anda melihat ada perubahan pada bentuk tahi lalat, segera temui dokter.\n" +
                "\n" +
                "Pada sebagian besar kasus yang ada, jaringan tahi lalat yang dianggap mencurigakan akan diangkat dengan pembedahan dan dipelajari apakah telah menjadi kanker. Proses ini dikenal dengan istilah biopsi. Sedangkan untuk memeriksa apakah melanoma sudah menyebar ke bagian tubuh lainnya, prosedur biopsi nodus sentinel juga bisa dilakukan.")
        disSympt.add("Dalam sebagian besar kasus, nevus melanositik benar-benar tanpa gejala . Namun, beberapa jenis salju dapat menimbulkan rasa gatal atau perasaan tidak nyaman . Dalam beberapa kasus, penampakan gejala-gejala tersebut tidak menimbulkan kekhawatiran, sementara dalam situasi lain simptomatologi yang disebutkan di atas dapat mewakili tanda evolusi menuju bentuk ganas.\n" +
                "\n" +
                "Dalam situasi seperti itu, perlu untuk menghubungi dokter kulit segera, karena ini adalah satu-satunya sosok yang dapat membuat diagnosis yang benar dan menetapkan apakah pantas untuk khawatir atau tidak.")
        disSympt.add("Setiap tipe vascular memiliki ciri-ciri yang berbeda beda.")
    }

}