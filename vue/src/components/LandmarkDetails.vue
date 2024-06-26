<template>
    <body>
        <div>
            <div id="title-image-container">
                <img id="image" v-bind:src="image" v-bind:alt="landmark.name" />
                <h1 id="title">{{ landmark.name }}</h1>
                <div class="likes-dislikes">
                    <!-- Thumbs-up icon for Likes button -->

                    <button id="likes" v-on:click="likeLandmark()">
                        <i id="thumbs-up" class="fas fa-thumbs-up" :class="{ liked: likeIt === 1 }"></i> {{
                            landmark.likeCount }}
                    </button>

                    <!-- Thumbs-down icon for Dislikes button -->
                    <button id="dislikes" v-on:click="dislikeLandmark(landmark)">
                        <i id="thumbs-down" class="fas fa-thumbs-down" :class="{ disliked: likeIt === -1 }"></i> {{
                            landmark.dislikeCount }}
                    </button>
                </div>
            </div>
            <p id="category">Category: {{ landmark.category }}</p>
            <p id="description">{{ landmark.description }}</p>
            <section>
                <div id="hours-container">
                    <div id="weekday">
                        <h2>Weekday Hours</h2>
                    </div>
                    <div id="weekdayhours">
                        <p>Opens: {{ formatTime(landmark.weekdayOpen) }} | </p>
                        <p class="close-w"> Closes: {{ formatTime(landmark.weekdayClose) }}</p>
                    </div>
                    <div id="weekend">
                        <h2>Weekend Hours</h2>
                    </div>
                    <div id="weekendhours">
                        <p>Opens: {{ formatTime(landmark.weekendOpen) }} | </p>
                        <p class="close-w"> Closes: {{ formatTime(landmark.weekendClose) }}</p>
                    </div>
                </div>
                <div id="website-text">
                    <h2>For additional information</h2>
                </div>
                <div id="website">
                    <a v-bind:href="landmark.website" target="_blank">Visit Landmark Website</a>
                </div>
            </section>
        </div>
    </body>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css';

export default {
    name: "LandmarkDetails",
    props: {
        landmark: {
            type: Object,
            required: true,
        },
        likeStatus: {
            type: Number,
        }
    },
    data() {
        return {
            likeIt: this.likeStatus
        }
    },
    computed: {
        image() {
            return (new URL(this.landmark.imagePath, import.meta.url)).href;
        },

    },
    watch: {
        likeStatus(newValue, oldValue) {
            this.likeIt = newValue;
        }
    },
    methods: {
        formatTime(timeString) {
            if (!timeString) {
                return 'unavailable';
            }
            // Parse the time string into hours, minutes, and seconds
            const [hours, minutes] = timeString.split(':').map(Number);
            // Create a Date object to handle conversion to AM/PM format
            const date = new Date();
            date.setHours(hours);
            date.setMinutes(minutes);
            // Get hours and minutes in 12-hour format
            let formattedHours = date.getHours() % 12;
            formattedHours = formattedHours === 0 ? 12 : formattedHours; // Convert 0 to 12
            const formattedMinutes = (date.getMinutes() < 10 ? '0' : '') + date.getMinutes(); // Add leading zero if needed
            // Determine whether it's AM or PM
            const amOrPm = date.getHours() < 12 ? 'AM' : 'PM';
            // Return the formatted time string
            return `${formattedHours}:${formattedMinutes} ${amOrPm}`;
        },
        likeLandmark() {
            this.$emit('update-likes', this.landmark);

        },
        dislikeLandmark() {
            this.$emit('dislike-landmark', this.landmark);

        }
    }
}
</script>

<style scoped>
#title-image-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

#image {
    height: 60%;
    width: 60%;
    object-fit: contain;
    border: 1px solid #708090;
    border-radius: 4px;
}

#title {
    display: block;
    justify-content: center;
    align-items: center;
    font-size: 3em;
    text-align: center;
    margin-bottom: 5px;
    margin-top: 0px;
    color: #fffff0;
}

#category {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1 em;
    text-align: center;
    margin-bottom: 20px;
    margin-top: 5px;
    color: #d7b740;
    padding-bottom: 5px;
    padding-top: 5px;
    background-color: #708090;
    border-radius: 15px;
    text-decoration: #292929 underline;
    text-decoration-style: solid;
}

.likes-dislikes {
    display: flex;
    justify-content: space-between;
}

#likes {
    display: flex;
    justify-content: left;
    align-items: center;
    font-size: 1.5em;
    text-align: center;
    margin-bottom: 5px;
    margin-top: 0px;
    color: #fffff0;
    border-bottom: 3px solid #708090;
    margin-right: 2%;
    background-color: #d7b740;
}

#dislikes {
    display: flex;
    justify-content: left;
    align-items: center;
    font-size: 1.5em;
    text-align: center;
    margin-bottom: 5px;
    margin-top: 0px;
    color: #fffff0;
    border-bottom: 3px solid #708090;
    margin-left: 2%;
    background-color: #d7b740;
}


.likes-button {
    font-size: 1.5em;
    text-align: center;
    margin-bottom: 5px;
    margin-top: 0px;
    color: #fffff0;
    border-bottom: 3px solid #708090;
    margin-right: 2%;
    background-color: #d7b740;
}

button {
    width: 130px;
    display: flex;
    align-items: center;
    justify-content: center;
}

button:hover {
    cursor: pointer;
}

button i {
    margin-right: 10px;
    vertical-align: middle;
}

.liked {
    color: green;
}

.disliked {
    color: red;
}

#description {
    display: flex;
    justify-content: left;
    align-items: center;
    font-size: 1.5em;
    text-align: center;
    margin-bottom: 0px;
    margin-top: 0px;
    color: #fffff0;
    border-bottom: 3px solid #708090;
}

#hours-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    border-bottom: #708090 2px solid;
}

#weekday {
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-right: 5px;
    margin-left: 15px;
    font-size: .8em;
    border: #708090 1px solid;
    padding-right: 5px;
    padding-left: 5px;
    background-color: rgba(112, 128, 144, 0.2);
}

#weekdayhours {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-right: 15px;
    margin-left: 15px;
}

#weekend {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-right: 5px;
    margin-left: 15px;
    font-size: .8em;
    border: #708090 1px solid;
    padding-right: 5px;
    padding-left: 5px;
    background-color: rgba(112, 128, 144, 0.2);
}

.close-w {
    padding-left: 3px;
}

#weekendhours {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-right: 15px;
    margin-left: 15px;
}

#website-text {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: .5em;
    text-align: center;
    margin-bottom: 0px;
    margin-top: 0px;
    color: #fffff0;
}

#website {
    text-align: center;
    color: #708090;
    font-size: .7em;
    text-decoration: #292929 underline;
    text-decoration-style: solid;
    margin-bottom: 20px;
    margin-top: 0px;
}

#website a:hover {
    color: #fffff0;
}

#website a {
    text-decoration: none;
    font-size: 1.5em;
    color: #4285f4;
}
</style>