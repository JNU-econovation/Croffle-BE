import sys
import json
from transformers import AutoProcessor, MusicgenForConditionalGeneration
import scipy.io.wavfile
import os


def generate_music(speed, mood, loc, date):

    model = MusicgenForConditionalGeneration.from_pretrained("facebook/musicgen-small")
    processor = AutoProcessor.from_pretrained("facebook/musicgen-small")

    inputs = processor(
        #일단 mod 값으로 실행
        text=[mood],
        padding=True,
        return_tensors="pt",
    )

    audio_values = model.generate(do_sample=True, guidance_scale=3, max_new_tokens=256)
    sampling_rate = model.config.audio_encoder.sampling_rate

    print(os.path.dirname(os.path.realpath(__file__)))

    # 현재 작업 디렉토리 경로를 가져옵니다.
    current_dir = os.getcwd()

    # 'music' 폴더가 있는지 확인하고, 없다면 생성합니다.
    music_dir = os.path.join(current_dir, "musics")
    os.makedirs(music_dir, exist_ok=True)

    # 파일 경로를 형성합니다.
    output_path = os.path.join(music_dir, "music" +date + ".wav")

    scipy.io.wavfile.write(
        output_path, rate=sampling_rate, data=audio_values[0, 0].numpy()
    )


if __name__ == "__main__":
    json_str = sys.stdin.read()
    data = json.loads(json_str)

    speed = data["speed"]
    mood = data["mood"]
    loc = data["loc"]
    date = data["date"]
    generate_music(speed, mood, loc, date)
