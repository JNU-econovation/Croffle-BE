import sys
import json
from transformers import AutoProcessor, MusicgenForConditionalGeneration
import scipy.io.wavfile
import os
import uuid
import warnings


def generate_music(speed, mood, loc):

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


    # 현재 작업 디렉토리 경로를 가져옵니다.
    current_dir = os.getcwd()

    # 'music' 폴더가 있는지 확인하고, 없다면 생성합니다.
    music_dir = os.path.join(current_dir, "musics")
    os.makedirs(music_dir, exist_ok=True)

    # UUID 버전 4 생성
    uuid_v4 = uuid.uuid4()
    uuid_str = str(uuid_v4)
    print(uuid_str)

    # 파일 경로를 형성합니다.
    output_path = os.path.join(music_dir, "music" + uuid_str + ".wav")

    scipy.io.wavfile.write(
        output_path, rate=sampling_rate, data=audio_values[0, 0].numpy()
    )


if __name__ == "__main__":
    warnings.filterwarnings('ignore')
    json_str = sys.stdin.read()
    data = json.loads(json_str)

    speed = data["speed"]
    mood = data["mood"]
    loc = data["loc"]
    generate_music(speed, mood, loc)
